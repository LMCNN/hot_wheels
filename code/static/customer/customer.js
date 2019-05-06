function QueryStringToJSON(str) {
    var pairs = str.split('&');
    var result = {};
    pairs.forEach(function(pair) {
        pair = pair.split('=');
        result[pair[0]] = decodeURIComponent(pair[1] || '');
    });
    return JSON.parse(JSON.stringify(result));
}

$(document).ready(function () {
    var currentCustomer = {};

    $('#CustomerTableContainer').jtable({
        title: 'Customers',
        fields: {
            id: {
                title: 'Id',
                key: true,
                list: false
            },
            Phones: {
                title: '',
                width: '1%',
                sorting: false,
                edit: false,
                create: false,
                display: function (customerData) {
                    //console.log(customerData.record.id);
                    //Create an image that will be used to open child table
                    var $img = $('<img src="../images/phone.png" title="Numbers" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#CustomerTableContainer').jtable('openChildTable',
                            $img.closest('tr'),
                            {
                                title: 'Phone numbers',
                                actions: {
                                    listAction: function(postData, jtParams){
                                        console.log(customerData.record.name);
                                        return $.Deferred(function ($dfd){
                                            $.ajax({
                                                url: '/tel?id=' + customerData.record.id,
                                                type: 'GET',
                                                success: function(data){
                                                    $dfd.resolve({"Result": "OK", "Records": data, "TotalRecordCount": data.length});
                                                },
                                                error: function(){
                                                    $dfd.reject();
                                                }
                                            });
                                        });
                                    }
                                },
                                fields: {
                                    tel_number: {
                                        title: customerData.record.name + ' - Phone Number',
                                        width: '30%'
                                    }
                                }
                            }, function (data) { //opened handler
                                data.childTable.jtable('load');
                            });
                    });
                    //Return image to show on the person row
                    return $img;
                }
            },
            Cars: {
                title: '',
                width: '1%',
                sorting: false,
                edit: false,
                create: false,
                display: function (customerData) {
                    //console.log(customerData.record.id);
                    //Create an image that will be used to open child table
                    var $img = $('<img src="../images/car.png" title="Cars" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                       currentCustomer['id'] = customerData.record.id;
                       console.log(currentCustomer['id']);
                       currentCustomer['name'] = customerData.record.name;
                        $('#CarTableContainer').jtable('load');
                    });
                    //Return image to show on the person row
                    return $img;
                }
            },
            name: {
                title: 'Name'
            },
            address: {
                title: 'Address'
            }
        },
        actions: {
            listAction: function(postData, jtParams){
                return $.Deferred(function ($dfd){
                    $.ajax({
                        url: '/customer',
                        type: 'GET',
                        success: function(data){
                            $dfd.resolve({"Result": "OK", "Records": data, "TotalRecordCount": data.length});
                        },
                        error: function(){
                            $dfd.reject();
                        }
                    });
                });
            },
            createAction: function (postData) {
                postData = QueryStringToJSON(postData);
                return $.Deferred(function ($dfd) {
                    $.ajax({
                        url: '/addCustomer',
                        type: 'POST',
                        contentType: "application/json; charset=utf-8",
                        data: JSON.stringify(postData),
                        dataType: 'json',
                        success: function (data) {
                            $dfd.resolve({ "Result": "OK", "Record": data });
                        },
                        error: function (xhr, options, error) {
                            $dfd.reject();
                        }
                    });
                });
            },
            deleteAction: function (postData) {
                return $.Deferred(function ($dfd) {
                    $.ajax({
                        url: '/deleteCustomer',
                        type: 'POST',
                        contentType: "application/json; charset=utf-8",
                        data: JSON.stringify(postData),
                        dataType: 'json',
                        success: function (data) {
                            $dfd.resolve({ "Result": "OK", "Record": data });
                        },
                        error: function () {
                            $dfd.reject();
                        }
                    });
                });
            }
            // updateAction: function(postData) {
            //     postData = QueryStringToJSON(postData);
            //     console.log("updating from custom function...");
            //     return $.Deferred(function ($dfd) {
            //         $.ajax({
            //             url: '/updateCustomer',
            //             type: 'POST',
            //             contentType: "application/json; charset=utf-8",
            //             data: JSON.stringify(postData),
            //             dataType: 'json',
            //             success: function (data) {
            //                 $dfd.resolve({ "Result": "OK", "Record": data });
            //             },
            //             error: function () {
            //                 $dfd.reject();
            //             }
            //         });
            //     });
            // }
        }
    });
    $('#CustomerTableContainer').jtable('load');

    $('#CarTableContainer').jtable({
        fields:{
            maintenance: {
                title: '',
                width: '1%',
                edit: false,
                create: false,
                display: function (carData) {
                    console.log('car id: ' + carData.record.id);
                    //Create an image that will be used to open child table
                    var $img = $('<img src="../images/maintenance.png" title="Maintenance" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#CarTableContainer').jtable('openChildTable',
                            $img.closest('tr'),
                            {
                                title: 'Maintenance record',
                                actions: {
                                    listAction: function(postData, jtParams){
                                        console.log('current repair id: ' + carData.record.id);
                                        return $.Deferred(function ($dfd){
                                            $.ajax({
                                                url: '/getCarRecords?id=' + carData.record.id,
                                                type: 'GET',
                                                success: function(data){
                                                    $dfd.resolve({"Result": "OK", "Records": data, "TotalRecordCount": data.length});
                                                },
                                                error: function(){
                                                    $dfd.reject();
                                                }
                                            });
                                        });
                                    }
                                },
                                fields: {
                                    mechanic: {
                                        title: '',
                                        width: '1%',
                                        edit: false,
                                        create: false,
                                        display: function (maintenanceData) {
                                            //Create an image that will be used to open child table
                                            var $img = $('<img src="../images/mechanic.png" title="Mechanic" />');
                                            //Open child table when user clicks the image
                                            $img.click(function () {
                                                $('#CarTableContainer').jtable('openChildTable',
                                                    $img.closest('tr'),
                                                    {
                                                        title: "Mechanic's name",
                                                        actions: {
                                                            listAction: function(postData, jtParams){
                                                                return $.Deferred(function ($dfd){
                                                                    $.ajax({
                                                                        url: '/getmm?id=' + maintenanceData.record.id,
                                                                        type: 'GET',
                                                                        success: function(data){
                                                                            $dfd.resolve({"Result": "OK", "Records": data, "TotalRecordCount": data.length});
                                                                        },
                                                                        error: function(){
                                                                            $dfd.reject();
                                                                        }
                                                                    });
                                                                });
                                                            }
                                                        },
                                                        fields: {
                                                            name: {
                                                                title: "mechanic's name",
                                                                width: '30%'
                                                            }
                                                        }
                                                    }, function (data) { //opened handler
                                                        data.childTable.jtable('load');
                                                    });
                                            });
                                            //Return image to show on the person row
                                            return $img;
                                        }
                                    },
                                    repair_type: {
                                        title: '',
                                        width: '1%',
                                        edit: false,
                                        create: false,
                                        display: function (maintenanceData) {
                                            //Create an image that will be used to open child table
                                            var $img = $('<img src="../images/part.png" title="Repair Type" />');
                                            //Open child table when user clicks the image
                                            $img.click(function () {
                                                $('#CarTableContainer').jtable('openChildTable',
                                                    $img.closest('tr'),
                                                    {
                                                        title: 'Repair Type',
                                                        actions: {
                                                            listAction: function(postData, jtParams){
                                                                console.log('current maintenance id: ' + maintenanceData.record.id);
                                                                console.log('maintenance date: ' + maintenanceData.record.record_date);
                                                                return $.Deferred(function ($dfd){
                                                                    $.ajax({
                                                                        url: '/getmt?id=' + maintenanceData.record.id,
                                                                        type: 'GET',
                                                                        success: function(data){
                                                                            $dfd.resolve({"Result": "OK", "Records": data, "TotalRecordCount": data.length});
                                                                        },
                                                                        error: function(){
                                                                            $dfd.reject();
                                                                        }
                                                                    });
                                                                });
                                                            }
                                                        },
                                                        fields: {
                                                            description: {
                                                                title: 'Repair Description',
                                                                width: '30%'
                                                            },
                                                            hours: {
                                                                title: 'Repair Time',
                                                                width: '30%'
                                                            }
                                                        }
                                                    }, function (data) { //opened handler
                                                        data.childTable.jtable('load');
                                                    });
                                            });
                                            //Return image to show on the person row
                                            return $img;
                                        }
                                    },
                                    id: {
                                        title: 'Maintenance Id',
                                        key: true,
                                        width: '30%'
                                    },
                                    record_date: {
                                        title: 'Maintenance date',
                                        width: '30%'
                                    }
                                }
                            }, function (data) { //opened handler
                                data.childTable.jtable('load');
                            });
                    });
                    //Return image to show on the person row
                    return $img;
                }
            },
            id:{
                title: 'car_id',
                key: true
            },
            year:{
                title: 'year'
            },
            make:{
                title: 'title'
            },
            model:{
                title: 'model'
            },
            customer_id:{
                title: 'customer_id',
                list:false
            }
        },
        actions:{
            listAction: function(postData, jtParams){
                return $.Deferred(function ($dfd){
                    $.ajax({
                        url: '/car?id=' + currentCustomer['id'],
                        type: 'GET',
                        success: function(data){
                            $dfd.resolve({"Result": "OK", "Records": data, "TotalRecordCount": data.length});
                        },
                        error: function(){
                            $dfd.reject();
                        }
                    });
                });
            }
        }
    });
});