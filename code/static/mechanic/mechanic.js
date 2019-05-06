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
    $('#MechanicTableContainer').jtable({
        title: 'Mechanics',
        fields: {
            id: {
                title: 'Id',
                key: true,
                list: false
            },
            Certifications: {
                title: '',
                width: '1%',
                sorting: false,
                edit: false,
                create: false,
                display: function (mechanicData) {
                    console.log(mechanicData.record.id);
                    //Create an image that will be used to open child table
                    var $img = $('<img src="../images/certification.png" title="Certifications" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#MechanicTableContainer').jtable('openChildTable',
                            $img.closest('tr'),
                            {
                                title: 'certifications',
                                actions: {
                                    listAction: function(postData, jtParams){
                                        console.log(mechanicData.record.name);
                                        return $.Deferred(function ($dfd){
                                            $.ajax({
                                                url: '/mce?id=' + mechanicData.record.id,
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
                                        title: mechanicData.record.name + ' - Certifications',
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

            name: {
                title: 'Name'
            },
            hourly_rate: {
                title: 'Hourly Rate'
            }
        },
        actions: {
            listAction: function(postData, jtParams){
                return $.Deferred(function ($dfd){
                    $.ajax({
                        url: '/me',
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
            // createAction: function (postData) {
            //     postData = QueryStringToJSON(postData);
            //     return $.Deferred(function ($dfd) {
            //         $.ajax({
            //             url: '/addCustomer',
            //             type: 'POST',
            //             contentType: "application/json; charset=utf-8",
            //             data: JSON.stringify(postData),
            //             dataType: 'json',
            //             success: function (data) {
            //                 $dfd.resolve({ "Result": "OK", "Record": data });
            //             },
            //             error: function (xhr, options, error) {
            //                 $dfd.reject();
            //             }
            //         });
            //     });
            // },
            // deleteAction: function (postData) {
            //     return $.Deferred(function ($dfd) {
            //         $.ajax({
            //             url: '/deleteCustomer',
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
    $('#MechanicTableContainer').jtable('load');

});