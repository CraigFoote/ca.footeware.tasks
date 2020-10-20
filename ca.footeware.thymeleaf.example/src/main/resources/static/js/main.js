/**
 * A simple GET and element update.
 */
function getWeather() {
	$.ajax("http://localhost:8080/weather").done(function(data) {
		$("#getWeatherResult").html(data);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert(textStatus);
	});
}

/**
 * Post a value in the URL rather than using RequestBody.
 */
function setWeather() {
	$.post("http://localhost:8080/weather/Sunny").done(function(data) {
		$("#setWeatherResult").html(data);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert(textStatus);
	});
}

/**
 * Delete a value in the URL rather than using RequestBody.
 */
function deleteWeather() {
	$.ajax({
		url: "http://localhost:8080/weather/Sleet",
		type: 'DELETE'
	}).done(function(data) {
		$("#deleteWeatherResult").html("You deleted " + data);
	});
}