$(document).ready(function(){
    console.log("Loaded!");
});



var albums = ['test1', 'test2', 'test3', 'test4', 'test5','test6', 'test7', 'test8', 'test9', 'test10'];


for(var i= 0, len = albums.length; i < len; i++){
    var html = '<li>';
    html  += '<figure>';

    var src = '../assets/images/' + albums[i] +'.jpg';
    console.log(src);
    html += '<img alt="'+ albums[i] +'"  title="'+ albums[i] +'" src="'+ src +'" />';
    html += '<figcaption>'+albums[i]+'</figcaption>';
    html += '</figure>';
    html += '</li>';
    $(".albums").append(html);
}

$('.albums li').show();