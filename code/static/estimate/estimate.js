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
    $('#EstimateTypeTableContainer').jtable({
        title: 'Repair Types',
        fields: {
            repair_type: {
                title: 'Repair Type'
            },
            mechanic_name: {
                title: 'Mechanic name'
            },
            hours: {
                title: 'hours'
            },
            hourly_rate: {
                title: "Mechanic's hourly rate"
            },
            part_cost: {
                title: 'Part Cost'
            },
            total_cost: {
                title: 'Total Cost'
            }
        },
        actions: {
            listAction: function(postData, jtParams){
                return $.Deferred(function ($dfd){
                    $.ajax({
                        url: '/estimate?type=' + document.getElementById("type").value,
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

    $('#EstimateTypeTableContainer').jtable('load');
});

function fetchEstimate() {
    $('#EstimateTypeTableContainer').jtable('load');
}
