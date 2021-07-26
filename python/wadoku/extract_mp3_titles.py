from mutagen.easyid3 import EasyID3
import glob
import os


ofile = open('mp3_titles.txt', 'w', encoding='utf-8')

files = glob.glob("/ws4/Accent/audio/*.mp3")
for file in files:
    name = os.path.basename(file)
    id = name[0:-4]
    mp3 = EasyID3(file)
    title = mp3['title'][0]

    ofile.write(id)
    ofile.write(';')
    ofile.write(title)
    ofile.write('\n')

ofile.close()
