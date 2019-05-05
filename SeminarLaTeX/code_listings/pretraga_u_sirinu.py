def pretraga_u_sirinu(pocetni, zavrsni, bridovi):
  pregledani = set()
  pronadeni = Queue()
  prethodni = dict()
  
  pronadeni.put(pocetni)

  while not pronadeni.empty():
    trenutni = pronadeni.get()
  
    if trenutni == zavrsni:
      return procitaj_put(pocetni, zavrsni, prethodni)
  
    for sljedeci in bridovi[trenutni]:
      if sljedeci not in pregledani:
        pregledani.add(sljedeci)
        pronadeni.put(sljedeci)
        prethodni[sljedeci] = trenutni
  
  return None # Ne postoji put između početnog i završnog.