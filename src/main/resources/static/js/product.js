const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },
};

const URLS = {
    products: '/products/all',
};

const toString = ({id, brand, model, price, image, owned}) => {
    let columns = `
    <td>${brand}</td>
    <td>${model}</td>
    <td>${price}</td>
    <td>${image}</td>
`
    columns += owned
        ? '<td></td>'
        : `<td>
            <form class="buy-item-form" data-id=${id} action="/products/buy/${id}" method="post">
                <button class="btn btn-info">Buy</button>
            </form>
           </td>`
    return `<tr>${columns}</tr>`
};

loader.show();
fetch(URLS.products)
    .then(response => response.json())
    .then(products => {
        let result = '';
        products.forEach(product => {
            const productString = toString(product);
            result += productString;
        });

        $('#items-table').html(result);
        loader.hide();
    });

$('#items-table').on('submit', '.buy-item-form', function (ev) {
    const url = $(this).attr('action');

    loader.show();
    fetch(url, {method: 'post'})
        .then(data => {
            console.log(data)
            loader.hide();
            window.location = '/products/all';
        });

    ev.preventDefault();
    return false;
});

const API_KEY = 'vOj2aP4GDvKMetSD490cdgCEOM8X5Pc35R7ipLgd';

fetch(`https://api.nal.usda.gov/fdc/v1/search?api_key=${API_KEY}`, {
    method: 'post',
    body: JSON.stringify({
        generalSearchInput: 'pizza',
    })
})
    .then(resp => resp.json())
    .then(x => window.location.reload());