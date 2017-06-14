/**
 * Created by scheldejonas on 10/03/17.
 */
var xhrAddressesRequest = new XMLHttpRequest();
xhrAddressesRequest.onreadystatechange = function () {
    if (xhrAddressesRequest.readyState === 4 && xhrAddressesRequest.status === 200) {
        var table = document.querySelector('.addressTable');
        console.log(table);
        var tableBody = document.querySelector('.addressTableBody');
        console.log(tableBody);
        var addressObjectModel = JSON.parse(xhrAddressesRequest.responseText);
        console.log(addressObjectModel);
        var tableBodyHtml = '';
        for (var i = 0; i < addressObjectModel.length; i += 1) {
            tableBodyHtml += '<tr>';
            for (var property in addressObjectModel[i]) {
                tableBodyHtml += '<td>' + addressObjectModel[i][property] + '</td>';
            }
            tableBodyHtml += '</tr>';
        }
        console.log(tableBodyHtml);
        tableBody.innerHTML = tableBodyHtml;
    }
}
xhrAddressesRequest.open('GET', 'http://localhost:8080/api/addresses/100?arguments=fname,lname,street,city');
xhrAddressesRequest.send();