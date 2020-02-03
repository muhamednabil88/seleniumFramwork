Feature: User Registration
	I want to check that the user can register in our nopcommerce website.
	
	Scenario Outline: User Registeration
	Given The user is in home page
	When Click on register link
	And I entered "<firstName>" , "<lastName>" , "<email>" , "<password>" 
	Then The Registeration page displayed success
	
	Examples:
	| firstName | lastName | email | password |
	| tamer | shams | vfdcdgggggvh@yahoo.com | 42424342347 |
	|jvbhcxj | hjvcf |dvdvdavffdvdvd@yahoo.com | 3234234243 |