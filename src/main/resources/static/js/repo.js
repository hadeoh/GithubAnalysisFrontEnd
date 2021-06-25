var table;
$(document).ready(function() {
    $('#example').DataTable();
    table = $('#search-table').DataTable({
        "columnDefs": [{ "targets": -1, "data": null, "defaultContent": "<input id='btnDetails' class='btn btn-success' width='25px' value='Get Details' />"}]
    });

    $("#search").keyup(function() {
       let query = $("#search").val();
       let url = "/repos/search-results?query=" + query;
       $.ajax({
           type: "GET",
           url,
           dataType: "json",
           beforeSend: function() {
               $(".image").show();
               $("#search-table").hide();
           },
           complete: function() {
               $(".image").hide();
               $("#search-table").show();
           }
       }).done(function (data) {
           let items = data.items;
           for (let i = 0; i < items.length; i++) {
               table.row.add([
                   items[i].name, items[i].owner.login, items[i].url
               ]).draw(false);
           }
       })
   });

    $('#search-table tbody').on('click', '[id*=btnDetails]', function () {
        var data = table.row($(this).parents('tr')).data();
        var name = data[0];
        var owner = data[1];
        window.location.href = "/repos/contributors/" + owner + "/" + name;
    });
});



//$(function() {
//    $('#search-table tbody').on('click', '[id*=btnDetails]', function () {
//        var data = table.row($(this).parents('tr')).data();
//        var name = data[0];
//        var owner = data[1];
//        alert("Name : " + name + "\n" + "Owner : " + owner + "\n");
//    });
//})