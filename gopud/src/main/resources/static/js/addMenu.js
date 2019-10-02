$(document).ready(function () {
    var counter = 0;

    $("#addrow").on("click", function () {
        var newRow = $("<tr>");
        var cols = "";

        cols += '<td><input type="text" class="form-control" name="nama' + counter + '"/></td>';
        cols += '<td><input type="text" class="form-control" name="deskripsi' + counter + '"/></td>';
        cols += '<td><input type="number" class="form-control" name="harga' + counter + '"/></td>';
        cols += '<td><input type="number" class="form-control" name="durasiMasak' + counter + '"/></td>';

        cols += '<td><input type="button" class="buttonDelete btn btn-md btn-danger "  value="Delete"></td>';
        newRow.append(cols);
        $("table.order-list").append(newRow);
        counter++;
    });

    $("table.order-list").on("click", ".buttonDelete", function (event) {
        $(this).closest("tr").remove();
        counter -= 1
    });

    //$("#addMenuForm").ajaxForm({url: '/menu/add', type: 'post'})

});