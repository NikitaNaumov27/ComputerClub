http://localhost:8080/swagger-ui/index.html

Get:
http://localhost:8080/api/clients 
http://localhost:8080/api/clients/idClient 
http://localhost:8080/api/computers 
http://localhost:8080/api/computers/idComputer 
http://localhost:8080/api/computers/free 
http://localhost:8080/api/tariffs 
http://localhost:8080/api/tariffs/idTariff 
http://localhost:8080/api/sessions 
http://localhost:8080/api/sessions/idSession 
http://localhost:8080/api/sessions/actives 

Post:
http://localhost:8080/api/clients/add 
{
    "clientName":"Иван",
    "age":21,
    "active":true
}
http://localhost:8080/api/computers/add 
{
    "number":"№PC",
    "status":true,
    "specification":"Intel core i5, …"
}
http://localhost:8080/api/tariffs/add 
{
    "tariffName": "Tariff#n",
    "price": 200,
    "description": "Description…"
}
http://localhost:8080/api/sessions/start/{idClient}/{idComputer}/{idTariff} 
{
No body
}

Patch:
http://localhost:8080/api/clients/idClient 
{
    "clientName":"Антонов",
    "age":17,
    "isActive":false
}
http://localhost:8080/api/computers/idComputer 
{
    "number":"№PC",
    "status":true,
    "specification":"Intel core i3, …"
}
http://localhost:8080/api/tariffs/idTariff 
{
    "tariffName": "TestName",
    "price": 222,
    "description": "Description …"
}
http://localhost:8080/api/sessions/end/idSession 
{
No body
}

Delete:
http://localhost:8080/api/clients/idClient 
http://localhost:8080/api/computers/idComputer 
http://localhost:8080/api/tariffs/idTariff 
http://localhost:8080/api/sessions/idSession 
