from django.db import models

# Create your models here.
class Account(models.Model):
    idUser = models.CharField(max_length=8,primary_key=True)
    userName = models.CharField(max_length=255)
    password = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    dateOfBirth = models.CharField(max_length=255)
    placeOfBirth = models.CharField(max_length=255)
    def getIDUser(self):
        return self.idUser
        pass
    def getUserName(self):
        return  self.userName
        pass
    def getPassword(self):
        return self.password
        pass
    def getName(self):
        return self.name
        pass
    def getDateOfBirth(self):
        return self.dateOfBirth
        pass
    def getPlaceOfBirth(self):
        return self.placeOfBirth
        pass
class Trip(models.Model):
    idTrip = models.CharField(max_length=8,primary_key=True)
    idUser = models.ForeignKey(Account, related_name="fk_Trip_User", on_delete = models.CASCADE)
    tripName = models.CharField(max_length=255)
    budget = models.FloatField(blank=True, null=True)
    startDate = models.CharField(max_length=16)
    endDate = models.CharField(max_length=16)
    departure = models.CharField(max_length=255)
    def getIDTrip(self):
        return self.idTrip
        pass
    def getIDUser(self):
        return self.idUser
        pass
    def getTripName(self):
        return  self.tripName
        pass
    def getBudget(self):
        return self.budget
        pass
    def getStartDate(self):
        return self.startDate
        pass
    def getEndDate(self):
        return self.endDate
        pass
    def getDeparture(self):
        return self.departure
        pass
    
class Expense(models.Model):
    idExpense = models.CharField(max_length=8,primary_key=True)
    idTrip = models.ForeignKey(Trip, related_name="fk_Expense_Trip", on_delete=models.CASCADE)
    expenseName = models.CharField(max_length=255)
    cost = models.FloatField(blank=True, null=True)
    typeExpense = models.IntegerField(blank=True, null=True)
    date = models.CharField(max_length=255, null=True)
    place = models.CharField(max_length=255,null=True)

    def getIDExpense(self):
        return self.idExpense
        pass
    def getIDTrip(self):
        return self.idTrip
        pass
    def getExpenseName(self):
        return  self.expenseName
        pass
    def getCost(self):
        return self.cost
        pass
    def getTypeExpense(self):
        return self.typeExpense
        pass
    def getDate(self):
        return self.date
        pass
    def getPlace(self):
        return self.place
        pass

class Share(models.Model):
    idTrip = models.ForeignKey(Trip, related_name="fk_Share_trip", on_delete=models.CASCADE)
    idUser = models.ForeignKey(Account, related_name="fk_Share_user", on_delete=models.CASCADE)
    def getIDTrip(self):
        return self.idTrip
        pass
    def getIDUser(self):
        return self.idUser
        pass