#vurrukkulluk

Voor details over het project zie: https://e-learning.educom.nu/cases/verrukkulluk/intro

##back office

De back office is gemaakt in java springboot REST API framework.
De algemenestructuur mappen zijn:

* model
  
  Een model is de class zoals deze opgesagen wordt in de database, dit wordt gedaan met hibernate.
* dto
  
  Een dto is de class zoals ze wordt verstuurt naar of ontvangenworden van de front office.
  
* controller

  Hier worden de requests van de front office ontvangen en verwerkt via het REST API framework.

* repository

  De repsitory maakt gebruik van de JpaRepository en geeft al standaard functies om data uit de database te halen.
  Met de mogenlijkheid om comstom queries te maken.
  
* service

  De service calsses maken gebruik van de repository. 

  
##front office

[To DO: beschrijven hoe frontend qua folder structuur in elkaar zit]

##deployment

[TO DO: welke stappen moet je doen om een build te maken van de front office, daarna mvn clean install voor back office doen, beschrijf het voor iemand die voor het eerst met de app werkt]
