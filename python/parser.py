import os
import requests
from html.parser import HTMLParser

# -------------------------------------------------------------------
def is_hiragana(ch):
  i = ord(ch)
  return (i >= 0x3041 and i <= 0x309F)


# -------------------------------------------------------------------    
def is_katakana(ch):
  i = ord(ch)
  return (i >= 0x30A0 and i <= 0x30FF)


# -------------------------------------------------------------------    
def is_kanji(ch):
  i = ord(ch)
  return (i >= 0x4e00 and i <= 0x9FAF) or (i >= 0x3400 and i <= 0x4DB5)


# -------------------------------------------------------------------
def is_japanese(data):
  if len(data) < 5:
    return False
  
  for i in range(5):
    ch = data[i]
    if is_kanji(ch) or is_hiragana(ch) or is_katakana(ch):
      return True

  return False


# -------------------------------------------------------------------
def get_href(attrs):
  for attr in attrs:
    if attr[0] == "href":
      return attr[1]


# -------------------------------------------------------------------
def download(url):
  name = os.path.basename(url)
  resp = requests.get(url)
  f = open(name, 'wb')
  f.write(resp.content)
  f.close()


# ------------------------------------------------------------
class DownloadSounds(HTMLParser):
  def handle_starttag(self, tag, attrs):
    if tag == "a":
      href = get_href(attrs)
      if href and href.endswith(".mp3"):
        print(href)
        download(href)

  def close(self):
    pass


# ------------------------------------------------------------
class ExtractText(HTMLParser):
  def __init__(self, path):
    super().__init__()
    self.inp = -1
    self.file = open(path, "w", encoding="utf-8")

  def close(self):
    self.file.close()

  def handle_starttag(self, tag, attrs):
    if tag == "p":
      self.inp = 0
    elif tag == "a":
      href = get_href(attrs)
      if href and href.endswith(".mp3"):
        print(os.path.basename(href), file=self.file)

  def handle_endtag(self, tag):
    if tag == "p" and self.inp:
      self.inp = -1

  def handle_data(self, data):
    if self.inp == 0:
      if is_japanese(data):
        self.inp = 1
        print(file=self.file)
        print(data.strip(), file=self.file)
      else:
        self.inp = -1
    elif self.inp == 1:
      print(data.strip(), file=self.file)


# ------------------------------------------------------------

f = open("c:\\tmp\\jap.html", "r", encoding="utf-8")
#f = open("d:\\Jap\\j4y\\t.txt", "r", encoding="utf-8")
data = f.read()
f.close()

#parser = DownloadSounds()
parser = ExtractText("t1.txt")
parser.feed(data)
parser.close()

