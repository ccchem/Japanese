def parseA(line):
  tokens = line.split('\t')
  if len(tokens) != 2:
    raise RuntimeError("Wrong number of tokens: " + line)

  idx = tokens[1].find("#ID=");
  if idx < 0:
    raise RuntimeError("Missing #ID=: " + line)

  ja = tokens[0][3:]
  en = tokens[1][:idx]
  id = tokens[1][idx + 4:-1]

  return id, ja, en

# -------------------------------------------------------------------


f = open('/ws4/Jap/Dic/examples.utf.txt', encoding='utf-8')

count = 0
for line in f:
  count += 1
  if count > 3: break

  if line.startswith('A: '):
    id, ja, en = parseA(line)
    print(id, ja, en)

f.close()

