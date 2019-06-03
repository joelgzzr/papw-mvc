$("#comment").keydown(function(e){
    // Enter was pressed without shift key
    if (e.keyCode == 13 && !e.shiftKey)
    {
        // prevent default behavior
        e.preventDefault();
    }
});

function insertComment(itemp, userp){
    commentp = $('#comment').val();
    if(commentp !== ''){
        $.post("comment", {
            item: itemp,
            user: userp,
            comment: commentp
        }, function(){
            location.reload();
        });
    }
}

function addCart(userp, itemp){
    $.post("add-cart", {
        item: itemp,
        user: userp,
    }, function(){
        location.reload();
    });
}

function checkout(cartidp, userp, itemp){
    $.post("checkout", {
        item: itemp,
        user: userp,
        cartid: cartidp
    }, function(){
        location.reload();
    });
}

function deleteCart(cartidp){
    $.post("delete-cart", {
        cartid: cartidp
    }, function(){
        location.reload();
    });
}

function getItemData(){
    itemidp = $('#editItem').val();
    $.post("form-item", {
        itemid: itemidp
    }, function(data){
        $('#editName').val(data.name);
        $('#editCategory').val(data.category);
        $('#editStock').val(data.stock);
        $('#editDesc').val(data.description);
        $('#editPrice').val(data.price);
    });
}

function rate(itemp, userp){
    var rating = 0;
    swal({
        title: 'Valora este articulo!',
        type: 'success',
        html:
          'Rating: <select class="swal2-input" id="swal-input1" name="rating">' +
                      '<option value="1" selected>1 Star</option>' +
                      '<option value="2">2 Stars</option>' +
                      '<option value="3">3 Stars</option>' +
                      '<option value="4">4 Stars</option>' +
                      '<option value="5">5 Stars</option>' +
                    '</select> <br>',
        focusConfirm: false,
        showCancelButton: true,
        allowOutsideClick: false,
        preConfirm: () => {
            rating = document.getElementById('swal-input1').value
        }
      }).then(()=>{
        $.post('rate-item', {
          rating: rating,
          item: itemp,
          user: userp
        }).then(()=>{
          swal(
              'Gracias!',
              'Gracias por tu valoracion!',
              'success'
          ).then(()=>{
              location.reload();
          });
        });
      });
}