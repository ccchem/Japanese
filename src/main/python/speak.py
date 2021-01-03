# pip install pywin32

import win32com.client as win32

spv = win32.Dispatch("SAPI.SpVoice")
vv = spv.GetVoices()

spv.Voice
spv.SetVoice(vv.Item(2))

spv.Speak("シロクマくん")

