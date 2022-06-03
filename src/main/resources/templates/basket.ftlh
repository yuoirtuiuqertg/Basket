<!DOCTYPE html>
<html>
<head>
    <title>Магазин "У Петровича"</title>
</head>
<body>

    <h1>Магазин "У Петровича"...<< Создать товар для потребительской корзины>> -  />

<h1>Товары нашего магазина : </h1>
<#list products as product>
    <div>
        <form action="/basket/${product.id}" method="get" >
        <p> ${product.id} <b>${product.name}</b> ${product.price}   ${product.quantity}   руб. <input type="submit" value="Очистить "/></p>
        </form>
    </div>

<#else>
    <h3>Товаров нет</h3>


</#list>
        <form action="/basket/delete" method="get" >
            <p>  <input type="submit" value="Очистить корзину "/></p>
        </form>
<hr>
        <h2>Cумма Заказа : ${price} руб.</h2>
        <h2>Количество товаров в  Заказе : ${quantity} штук</h2>
</body>
</html>