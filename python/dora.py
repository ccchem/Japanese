import os
import requests
import time

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
def dd_img():
  for i in range(120,200):
    num = "{:0>3d}".format(i)
    url = "https://official-complete-1.granpulse.us/manga/Doraemons-Long-Tales/0010-" + num + ".png"
    print(url)
    download(url)
    time.sleep(20)

# -------------------------------------------------------------------


dd_img()
