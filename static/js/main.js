(function() {
  var form = document.getElementById('file-input');
  var preview = document.getElementById('preview');

  var postPayload = function(obj) {
    var req = new XMLHttpRequest();
    req.open("POST", "/documents/extract-text");
    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    req.send(JSON.stringify(obj));
  };

  var loadImage = function(e) {
    var file = this.files[0];
    var reader = new FileReader();

    reader.onloadend = function() {
      var base64 = reader.result;
      console.log(base64);
      preview.src = base64;
      preview.style = "display: ''";

      postPayload({image: base64});
    }
    reader.readAsDataURL(file);
  };

  form.addEventListener('change', loadImage);
})()