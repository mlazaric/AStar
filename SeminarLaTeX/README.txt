LaTeX predlozak za seminarski rad (FER, 2014)
Autor: Matija Santl

SADRZAJ PAKETA:
1. Osnovni stil: fer.cls
2. Stil za bibliografiju: fer.bst
3. Osnovna tex datoteka: Seminar[godina]Prezima_Ime.tex
4. Popis literature: literatura.bib


UPUTE:
- preimenovati osnovnu tex biblioteku, editirati rad
- literaturu pisati u literatura.bib


"RUCNO" KREIRANJE PDF DATOTEKE (CMD LINE):

- klasicni nacin: 

	  latex		    dvips	     ps2pdf
text.tex -------> text.dvi -------> text.ps --------> text.pdf


- pdflatex:

	 pdflatex
text.tex --------> text.pdf


ALATI:
- preporuceni alat: TeXworks (http://www.tug.org/texworks/)

Napomena vezana uz ukljucivanje literature:
	
- unesite koristenu literaturu u literatura.bib datoteku
	
- seminar.tex najprije kompajlirati dvaput s BibTex-om
	
- seminar.tex zatim kompajlirati dvaput s pdfLaTeX-om
Na taj nacin ce se ispravno ukljuciti literatura u seminarski rad.

/eof