<#-- @ftlvariable name="" type="com.me.mancala.views.GameListView" -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Mancala</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<#list games as game>
    <a href="javascript:void(0)" class="game_id" id="game_id">${game}</a>
</#list>
<br/>
<label for="lower_player">Player 1 Name:</label><br>
<input type="text" id="lower_player" name="lower_player" value="John"><br>
<label for="upper_player">Player 2 Name:</label><br>
<input type="text" id="upper_player" name="upper_player" value="Doe"><br><br>
<button type="button" onclick="loadDoc()">Create Game</button>

<script>
function loadDoc() {
  var lower = $("#lower_player").val();
  var upper = $("#upper_player").val();
  $.ajax({
      contentType: 'application/json',
      data: JSON.stringify({ "lower_player": lower, "upper_player": upper }),
      dataType: 'json',
      success: function(data){
          window.location.replace("/games/"+data.game_uuid)
      },
      error: function(){
          console.log("failed!");
      },
      type: 'POST',
      url: '/games'
  });
}

$('.game_id').on("click",function(){
  window.location.replace("/games/"+$("#game_id").val())
})
</script>

</body>
</html>