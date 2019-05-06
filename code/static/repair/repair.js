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
    $('#RepairTypeTableContainer').jtable({
        title: 'Repair Types',
        fields: {
            id: {
                title: 'Id',
                key: true,
                list: false
            },
            Certification: {
                title: '',
                width: '1%',
                sorting: false,
                edit: false,
                create: false,
                display: function (repairData) {
                    //console.log(repairData.record.id);
                    //Create an image that will be used to open child table
                    var $img = $('<img src="../images/certification.png" title="Certification" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#RepairTypeTableContainer').jtable('openChildTable',
                            $img.closest('tr'),
                            {
                                title: 'Certification',
                                actions: {
                                    listAction: function(postData, jtParams){
                                        console.log('current repair id: ' + repairData.record.id);
                                        return $.Deferred(function ($dfd){
                                            $.ajax({
                                                url: '/getCertification?id=' + repairData.record.id,
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
                                        title: 'Certification',
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
            Parts: {
                title: '',
                width: '1%',
                sorting: false,
                edit: false,
                create: false,
                display: function (repairData) {
                    //console.log(repairData.record.id);
                    //Create an image that will be used to open child table
                    var $img = $('<img src="../images/part.png" title="Certification" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#RepairTypeTableContainer').jtable('openChildTable',
                            $img.closest('tr'),
                            {
                                title: 'Parts',
                                actions: {
                                    listAction: function(postData, jtParams){
                                        console.log('current repair id: ' + repairData.record.id);
                                        return $.Deferred(function ($dfd){
                                            $.ajax({
                                                url: '/getParts?id=' + repairData.record.id,
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
                                    cost: {
                                        title: 'cost',
                                        width: '30%'
                                    },
                                    name: {
                                        title: 'name',
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
            description: {
                title: 'description'
            },
            hours: {
                title: 'hours'
            },
            certification_id: {
                title: 'certification id',
                list: false
            }
        },
        actions: {
            listAction: function(postData, jtParams){
                return $.Deferred(function ($dfd){
                    $.ajax({
                        url: '/getTypes',
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

    $('#RepairTypeTableContainer').jtable('load');
});