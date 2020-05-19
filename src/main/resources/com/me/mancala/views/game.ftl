<#-- @ftlvariable name="" type="com.me.mancala.views.GameView" -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Mancala</title>
	<link rel="stylesheet" href="/css/game.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
</head>
<body data-player="one">
	<h1>Mancala</h1>

	<div class="board">
		<div class="player-two store" id="mancala2">${game.board.upperSide.largePit.stones}<p></p></div>

		<div class="rows">
			<div class="row player-two side2">
				<div class="pit" id="5"><p>${game.board.upperSide.pits[5].stones}</p></div>
				<div class="pit" id="4"><p>${game.board.upperSide.pits[4].stones}</p></div>
				<div class="pit" id="3"><p>${game.board.upperSide.pits[3].stones}</p></div>
				<div class="pit" id="2"><p>${game.board.upperSide.pits[2].stones}</p></div>
				<div class="pit" id="1"><p>${game.board.upperSide.pits[1].stones}</p></div>
				<div class="pit" id="0"><p>${game.board.upperSide.pits[0].stones}</p></div>
			</div>

			<div class="row player-one side1">
				<div class="pit" id="0"><p>${game.board.lowerSide.pits[0].stones}</p></div>
				<div class="pit" id="1"><p>${game.board.lowerSide.pits[1].stones}</p></div>
				<div class="pit" id="2"><p>${game.board.lowerSide.pits[2].stones}</p></div>
				<div class="pit" id="3"><p>${game.board.lowerSide.pits[3].stones}</p></div>
				<div class="pit" id="4"><p>${game.board.lowerSide.pits[4].stones}</p></div>
				<div class="pit" id="5"><p>${game.board.lowerSide.pits[5].stones}</p></div>
			</div>
		</div>

		<div class="player-one store" id="mancala1"><p>${game.board.lowerSide.largePit.stones}</p></div>
	</div>

    <#if game.isGameOver()>
      <p class="status">Winner is <span class="current-player">${game.winner.name}</span> </p>
    <#else>
      <p class="status"><span class="current-player">${game.currentPlayer.name}</span>'s turn</p>
    </#if>


	<script>
	$('.pit').on("click",function(){
      var pitIndex = $(this).attr('id');
      var sideIndex = $(this).parent().hasClass("side1") ? 0 : 1;
      $.ajax({
        contentType: 'application/json',
        data: JSON.stringify({ "game_id": "${game.uuid}", "side_index": sideIndex, "pit_index": pitIndex }),
        dataType: 'json',
        success: function(data){
            location.reload();
        },
        error: function(data){
            alert(data.responseText);
        },
        type: 'POST',
        url: '/moves/apply_move'
    });
    })
    </script>
</body>
</html>