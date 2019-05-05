def procitaj_put(pocetni, zavrsni, prethodni):
  put = []
  trenutni = zavrsni

  while pocetni != trenutni:
    put.append(trenutni)

    trenutni = prethodni[trenutni]

  put.append(pocetni)

  return list(reversed(put)) # Obrne poredak elemenata u listi.