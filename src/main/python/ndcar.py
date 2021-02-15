import os
import requests
import bs4

# -------------------------------------------------------------------
def download(url):
  name = os.path.basename(url)
  resp = requests.get(url)
  if resp.status_code != 200:
    print("Status: " + str(resp.status_code))
    return
  f = open(name, 'wb')
  f.write(resp.content)
  f.close()


# -------------------------------------------------------------------
def dd_voice():
  for i in range(300,400):
    url = "https://eng.nihongodecarenavi.jp/voice/entry/entry_" + str(i) + ".mp3"
    #url = "https://eng.nihongodecarenavi.jp/voice/example/example_" + str(i) + ".mp3"
    print(url)
    download(url)

# -------------------------------------------------------------------
def dd_entry(i, file):
  url = "https://eng.nihongodecarenavi.jp/jpn/entry_" + str(i) + ".html"
  resp = requests.get(url)
  if resp.status_code != 200:
    print("Status: " + str(resp.status_code))
    return

  soup = bs4.BeautifulSoup(resp.text, 'html.parser')
  kana = soup.find("h1", class_="kana").text.strip()
  kanji = soup.find("h2", class_="kanji").text.strip()
  eng = soup.find("h2", class_="eng").text.strip()
  print(str(i) + "|" + kana + "|" + kanji + "|" + eng, file=file)

# -------------------------------------------------------------------
def dd_entries():
  file = open("D:\\Jap\\ndcar\\entry.2300.txt", "w", encoding="utf-8")
  for i in range(2300,2400):
    print(i)
    dd_entry(i, file)
  file.close()


dd_entries()
