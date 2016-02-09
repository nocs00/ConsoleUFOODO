# ConsoleUFOODO

This project is REST API for automatic generating food menu considering parametres and objectives of person.

###To work with this API the first thing you need is Task.
**Task format (JSON) :**

```json
{
"userID" : "some_username",
"sex" : "male/female (default: male)",
"age" : "years_integer (default: 25)",
"height" : "cm/inches_double (default: 175cm)",
"weight" : "kg/pounds_double (default: 75kg)",
"activity_level" : "one_of: sedentary,light,moderate,high,extra_high (default:light)",
"body_type" : "one_of: fat,normal,athlete,thin (default: normal)",
"foodItems" : ["food_item_name1", "..."],
"metricUS" : "0/1 (default: 0 - metric[kg,cm])"
}
```

**Example :**

```json
{ 
"userID" : "masha90", 
"sex" : "female", 
"age" : 18, 
"height" : 160.0, 
"weight" : 53.0, 
"activity_level" : "light", 
"body_type" : "normal", 
"foodItems" : ["apple", "potato", "chicken"],
"metricUS" : 0
}
```



### Search endpoints:

<table>
  <tr>
    <td>Endpoint</td>
    <td>Request type</td>
    <td>Consumes</td>
    <td>Produces</td>
  </tr>
  <tr>
    <td>https://ufoodo.com/mongorest/control/search-fooditems</td>
    <td>PUT</td>
    <td>application/json;charset=utf-8</td>
    <td>text/plain</td>
  </tr>
  <tr>
    <td>https://ufoodo.com/mongorest/control/search-dishes</td>
    <td>PUT</td>
    <td>application/json;charset=utf-8</td>
    <td>text/plain</td>
  </tr>
</table>

