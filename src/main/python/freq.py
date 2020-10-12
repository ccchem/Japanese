import collections

class Frequency:
    def __init__(self):
        self.counter = collections.Counter()

    def isKanji(self, code):
        return (code >= 0x4e00 and code <= 0x9FAF) or (code >= 0x3400 and code <= 0x4DB5)

    def addText(self, text):
        for ch in text:
            if self.isKanji(ord(ch)):
                self.counter[ch] += 1

    def counters(self):
        return self.counter

# ----------------------------------------------------------------

fr = Frequency()

file = open('/ws4/Jap/Books/1Q84_BOOK01.txt', 'r', encoding="UTF-8")
for line in file:
    fr.addText(line)
file.close()

cnt = fr.counters()

for item in cnt.most_common(100):
    print("{}, {}".format(item[0], item[1]))
