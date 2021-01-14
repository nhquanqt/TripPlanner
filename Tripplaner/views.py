from django.shortcuts import render
from django.http import HttpResponse
from django.http import JsonResponse
from django.http import FileResponse
from .models import *
# Create your views here.
def Login(request):
    name = request.GET['u']
    passw = request.GET['p']
        
    ac = Account.objects.all()
    for acci in ac:
        truename = acci.getUserName()
        truepass = acci.getPassword()
        if name == truename and passw == truepass:
            obj = {"idUser": acci.getIDUser()}
            return JsonResponse(obj)
            break
        else:
            continue
    return HttpResponse('Login fail')
    
    pass

def getTripUser(request):
    id = request.GET['id']
    result = []
    trip = Trip.objects.all()
    for tripi in trip:
        idUser =  tripi.getIDUser()
        if id == idUser.idUser:
            obj = {"idTrip": tripi.getIDTrip(), "tripName": tripi.getTripName(), "budget": tripi.getBudget(), "startDate": tripi.getStartDate(), "endDate": tripi.getEndDate(), "departure": tripi.getDeparture()}
            result.append(obj)
    res = {"result": result}
    return JsonResponse(res)
    pass

def getExpenseTrip(request):
    id = request.GET['t']
    result = []
    expense = Expense.objects.all()
    for exi in expense:
        idTrip =  exi.getIDTrip()
        if id == idTrip.idTrip:
            obj = {"idExpense": exi.getIDExpense(), "expenseName": exi.getExpenseName(), "cost": exi.getCost(), "typeExpense": exi.getTypeExpense()}        
            result.append(obj)
    res = {"result": result}
    return JsonResponse(res)
    pass