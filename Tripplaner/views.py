from django.shortcuts import render
from django.http import HttpResponse
from django.http import JsonResponse
from django.http import FileResponse
from .models import *
# Create your views here.
def Login(request):
    uname = request.GET['u']
    passw = request.GET['p']
        
    ac = Account.objects.all()
    for acci in ac:
        truename = acci.getUserName()
        truepass = acci.getPassword()
        if uname == truename and passw == truepass:
            obj = {"idUser": acci.getIDUser(), "userName": acci.getUserName(), "name": acci.getName(), "dateOfBirth": acci.getDateOfBirth(), "placeOfBirth": acci.getPlaceOfBirth()}
            return JsonResponse(obj)
            break
        else:
            continue
    return HttpResponse('Login fail')
    
    pass

def getUserByID(request):
    id = request.GET['id']
    ac = Account.objects.all()
    for acci in ac:
        if id == acci.getIDUser():
            obj = {"idUser": acci.getIDUser(), "userName": acci.getUserName(), "name": acci.getName(), "dateOfBirth": acci.getDateOfBirth(), "placeOfBirth": acci.getPlaceOfBirth()}
            return JsonResponse(obj)
            break
        else:
            continue
    return HttpResponse('Fail')
    
    pass

def changeUserbyID(request):
    id = request.POST['id']
    name = request.POST['name']
    date = request.POST['date']
    place = request.POST['place']
    ac = Account.objects.all()
    for acci in ac:
        if id == acci.getIDUser():
            acci.name = name
            acci.dateOfBirth = date
            acci.placeOfBirth = place
            acci.save()
            obj = {"result": 1}
            return JsonResponse(obj)
            break
        else:
            continue
    obj = {"result": 0}
    return JsonResponse(obj)
    
    pass
def Signup(request):
    uname = request.GET['u']
    passw = request.GET['p']
    ID = 0    
    ac = Account.objects.all()

    for acci in ac:
        truename = acci.getUserName()
        ID = max(int(ID),int(acci.getIDUser()))
        if uname == truename :
            obj = {"idUser": ""}
            return JsonResponse(obj)
            break
        else:
            continue
    ID = str(int(ID) + 1)
    Account.objects.create(idUser = ID, userName = uname, password = passw)
    obj = {"idUser": ID}
    return JsonResponse(obj)
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

def getTripByID(request):
    id = request.GET['id']
    result = []
    trip = Trip.objects.all()
    for tripi in trip:
        idTrip =  tripi.getIDTrip()
        if id == idTrip.idTrip:
            obj = {"idTrip": tripi.getIDTrip(), "tripName": tripi.getTripName(), "budget": tripi.getBudget(), "startDate": tripi.getStartDate(), "endDate": tripi.getEndDate(), "departure": tripi.getDeparture()}
            result.append(obj)
    res = {"result": result}
    return JsonResponse(res)
    pass

def addTrip(request):
    idu = request.GET['idu']
    name = request.GET['name']
    bud = request.GET['bud']
    start = request.GET['start']
    end = request.GET['end']
    dep = request.GET['dep']

    ID = 0    
    trip = Trip.objects.all()
    for tripi in trip:
        ID = max(int(ID),int(tripi.getIDTrip())) 
    idUser = Account.objects.get(idUser=idu)
    ID = str(int(ID) + 1)
    Trip.objects.create(idTrip = ID, idUser = idUser, tripName=name, budget=bud, startDate=start, endDate=end, departure=dep)
    obj = {"idTrip": ID}
    return JsonResponse(obj)
    pass


def getExpenseTrip(request):
    id = request.GET['id']
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

def getExpensebyID(request):
    id = request.GET['id']
    result = []
    expense = Expense.objects.all()
    for exi in expense:
        idExpense =  exi.getIDExpense()
        if id == idExpense.idExpense:
            obj = {"idExpense": exi.getIDExpense(), "expenseName": exi.getExpenseName(), "cost": exi.getCost(), "typeExpense": exi.getTypeExpense()}        
            result.append(obj)
    res = {"result": result}
    return JsonResponse(res)
    pass

def addExpense(request):
    idt = request.GET['idt']
    name = request.GET['name']
    cost = request.GET['cost']
    typ = request.GET['type']

    ID = 0    
    ex = Expense.objects.all()
    for exi in ex:
        ID = max(int(ID),int(exi.getIDExpense())) 
    idTrip = Trip.objects.get(idTrip=idt)
    ID = str(int(ID) + 1)
    Expense.objects.create(idExpense= ID, idTrip = idTrip, expenseName=name, cost=cost, typeExpense=typ)
    obj = {"idExpense": ID}
    return JsonResponse(obj)
    pass