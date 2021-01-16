import json
import os
from django.shortcuts import render
from django.http import HttpResponse
from django.http import JsonResponse
from django.http import FileResponse
from django.views.decorators.csrf import csrf_exempt
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
            obj = {"id": acci.getIDUser(), "username": acci.getUserName(), "name": acci.getName(), "dateOfBirth": acci.getDateOfBirth(), "placeOfBirth": acci.getPlaceOfBirth()}
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
            obj = {"id": acci.getIDUser(), "username": acci.getUserName(), "name": acci.getName(), "dateOfBirth": acci.getDateOfBirth(), "placeOfBirth": acci.getPlaceOfBirth()}
            return JsonResponse(obj)
            break
        else:
            continue
    return HttpResponse('Fail')
    
    pass

@csrf_exempt
def updateUser(request):
    body_unicode = request.body.decode('utf-8')
    body = json.loads(body_unicode)

    id = body['id']
    name = body['name']
    dateOfBirth = body['dateOfBirth']
    placeOfBirth = body['placeOfBirth']
    Account.objects.filter(idUser=id).update(name=name,dateOfBirth=dateOfBirth, placeOfBirth=placeOfBirth)
    obj = {"result": True}
    return JsonResponse(obj)
    pass

@csrf_exempt
def Signup(request):
    body_unicode = request.body.decode('utf-8')
    body = json.loads(body_unicode)

    uname = body['username']
    passw = body['password']
    name = body['name']
    ID = 0    
    ac = Account.objects.all()

    for acci in ac:
        truename = acci.getUserName()
        ID = max(int(ID),int(acci.getIDUser()))
        if uname == truename :
            obj = {"result": False}
            return JsonResponse(obj)
            break
        else:
            continue
    ID = str(int(ID) + 1)
    Account.objects.create(idUser = ID, userName = uname, password = passw,name=name)
    obj = {"result": True}
    return JsonResponse(obj)
    pass

def getTripsByUserID(request):
    id = request.GET['userId']
    result = []
    listShareTripID = Share.objects.filter(idUser=id).all()
    
    trip = Trip.objects.all()

    for shareTripID in listShareTripID:
        tripi = Trip.objects.filter(idTrip=shareTripID.getIDTrip().idTrip).first()
        obj = {"id": tripi.getIDTrip(), "tripName": tripi.getTripName(), "budget": tripi.getBudget(), "startDate": tripi.getStartDate(), "endDate": tripi.getEndDate(), "departure": tripi.getDeparture()}
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
        if id == idTrip:
            obj = {"id": tripi.getIDTrip(), "tripName": tripi.getTripName(), "budget": tripi.getBudget(), "startDate": tripi.getStartDate(), "endDate": tripi.getEndDate(), "departure": tripi.getDeparture()}
            result.append(obj)
    res = {"result": result}
    return JsonResponse(res)
    pass

@csrf_exempt
def updateTrip(request):
    body_unicode = request.body.decode('utf-8')
    body = json.loads(body_unicode)

    id = body['id']
    name = body['tripName']
    bud = body['budget']
    start = body['startDate']
    end = body['endDate']
    dep = body['departure']
   
    Trip.objects.filter(idTrip=id).update(tripName=name, budget=bud, startDate=start,endDate=end,departure=dep)
    obj = {"result": True}
    return JsonResponse(obj)

@csrf_exempt
def addTrip(request):
    body_unicode = request.body.decode('utf-8')
    body = json.loads(body_unicode)

    idu = body['idUser']
    name = body['tripName']
    bud = body['budget']
    start = body['startDate']
    end = body['endDate']
    dep = body['departure']
    ID = 0    
    trip = Trip.objects.all()
    for tripi in trip:
        ID = max(int(ID),int(tripi.getIDTrip())) 
    idUser = Account.objects.get(idUser=idu)
    ID = str(int(ID) + 1)
    Trip.objects.create(idTrip = ID, idUser = idUser, tripName=name, budget=bud, startDate=start, endDate=end, departure=dep)
    obj = {"result": ID}
    return JsonResponse(obj)
    pass

@csrf_exempt
def deleteTripByID(request):
    body_unicode = request.body.decode('utf-8')
    body = json.loads(body_unicode)

    id = body['id']
    Trip.objects.filter(idTrip=id).delete()    
    obj = {"result": True}
    return JsonResponse(obj)
    pass


def getExpensebyIDTrip(request):
    id = request.GET['idTrip']
    result = []
    expense = Expense.objects.all()
    for exi in expense:
        idTrip =  exi.getIDTrip()
        if id == idTrip.idTrip:
            obj = {"id": exi.getIDExpense(), "expenseName": exi.getExpenseName(), "cost": exi.getCost(), "type": exi.getTypeExpense(), "date": exi.getDate(), "place": exi.getPlace()}        
            result.append(obj)
    res = {"result": result}
    return JsonResponse(res)
    pass

def getExpensebyID(request):
    id = request.GET['id']
    result = []
    expense = Expense.objects.all()
    for exi in expense:
        idExpense = exi.getIDExpense()
        if id == idExpense:
            obj = {"id": exi.getIDExpense(), "expenseName": exi.getExpenseName(), "cost": exi.getCost(), "type": exi.getTypeExpense(), "date": exi.getDate(), "place": exi.getPlace()}        
            result.append(obj)
    res = {"result": result}
    return JsonResponse(res)
    pass

@csrf_exempt
def updateExpense(request):
    body_unicode = request.body.decode('utf-8')
    body = json.loads(body_unicode)
    print(body)
    id = body['id']
    name = body['expenseName']
    cost = body['cost']
    typ = body['type']
    date = body['date']
    place = body['place']
    Expense.objects.filter(idExpense=id).update(expenseName=name, cost=cost, typeExpense=typ, date=date, place=place)
    obj = {"result": True}
    return JsonResponse(obj)

@csrf_exempt
def addExpense(request):

    body_unicode = request.body.decode('utf-8')
    body = json.loads(body_unicode)

    idt = body['idTrip']
    name = body['expenseName']
    cost = body['cost']
    typ = body['type']
    date = body['date']
    place = body['place']

    ID = 0    
    ex = Expense.objects.all()
    for exi in ex:
        ID = max(int(ID),int(exi.getIDExpense())) 
    idTrip = Trip.objects.get(idTrip=idt)
    ID = str(int(ID) + 1)
    Expense.objects.create(idExpense= ID, idTrip = idTrip, expenseName=name, cost=cost, typeExpense=typ, date=date, place=place)
    obj = {"result": True}
    return JsonResponse(obj)
    pass

@csrf_exempt
def deleteExpenseByID(request):
    body_unicode = request.body.decode('utf-8')
    body = json.loads(body_unicode)

    id = body['id']
    Expense.objects.filter(idExpense=id).delete()    
    obj = {"result": True}
    return JsonResponse(obj)
    pass

def getSharebyTripID(request):
    idTrip = request.GET['idTrip']
    result = []
    shares = Share.objects.all()
    for share in shares:
        if idTrip == share.getIDTrip().idTrip:
            obj = {"idTrip": share.getIDTrip().idTrip, "idUser": share.getIDUser().idUser}
            result.append(obj)
    res = {"result": result}
    return JsonResponse(res)
    pass


@csrf_exempt
def addShare(request):
    body_unicode = request.body.decode('utf-8')
    body = json.loads(body_unicode)
    print(body)
    idt = body['idTrip']
    idu = body['idUser']
    idTrip = Trip.objects.get(idTrip=idt)
    idUser = Account.objects.get(idUser=idu)
    Share.objects.create(idTrip= idTrip, idUser = idUser)
    obj = {"result": True}
    return JsonResponse(obj)
    pass


@csrf_exempt
def deleteShareByTripID(request):
    body_unicode = request.body.decode('utf-8')
    body = json.loads(body_unicode)
    idt = body['idTrip']
    idTrip = Trip.objects.get(idTrip=idt)
    Share.objects.filter(idTrip=idTrip).delete()
    obj = {"result": True}
    return JsonResponse(obj)
    pass