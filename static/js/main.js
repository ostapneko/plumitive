(function() {
var fileInput = document.getElementById('fileInput');
var preview = document.getElementById('preview');

var postPayload = function(endpoint, obj) {
    var req = new XMLHttpRequest();
    req.open("POST", endpoint);
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

        postPayload("/documents/extract-text", {image: base64});
    }

    reader.readAsDataURL(file);
};

fileInput.addEventListener('change', loadImage);

var form = document.getElementById('documentForm');

form.addEventListener('submit', function(ev) {
    ev.preventDefault();
    var payload = {};
    [
        "title",
        "month",
        "year",
        "sender"
    ].forEach(function(field) {
        payload[field] = document.getElementById(field).value;
    });

    [
        "tags",
        "recipients"
    ].forEach(function(field) {
        var value = document.getElementById(field).value;
        payload[field] = value.split(",").map(function(word) {
            return word.trim();
        });
    });

    postPayload("/document", payload)
});

})()