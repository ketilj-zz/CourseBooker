$("document").ready(function() {
	load_tweets();
	setInterval(load_tweets, 20000);
});



function load_tweets() {
	var last_ID = $(".tweet_result:first").attr("ID");
	

	var t = "agilemanager";
	
	if (last_ID) {
		last_ID = last_ID.replace("tweet", "");
		var url = "http://search.twitter.com/search.json?q=kanban+from:" + t + "&rpp=3&since=" + last_ID + "+callback=?";
	} else {
		//var url = "http://search.twitter.com/search.json?q=kanban&rpp=3&callback=?"; 
		var url = "http://search.twitter.com/search.json?q=kanban+from:agilemanager&rpp=3&callback=?";
	}
	$.getJSON(url, function(json) { 
		var results = '';
		$(json.results).each(function() {
			if (this.id == undefined) return;
			results += "<p class='tweet_result' id='tweet" + this.id + "'><a href='http://twitter.com/" + this.from_user + "' class='tweet_user'><img width='32' height='32' alt='" + this.from_user + " on Twitter' src='" + this.profile_image_url + "' /></a>" + linkify(this.text) + "</p>";
		});
		$("#twitter_results").prepend(results);
	});

	$(".tweet_result:gt(20)").remove();
}

function linkify(text) {
    // modified from TwitterGitter by David Walsh (davidwalsh.name)
    // courtesy of Jeremy Parrish (rrish.org)
    return text.replace(/(https?:\/\/[\w\-:;?&=+.%#\/]+)/gi, '<a href="$1">$1</a>')
               .replace(/(^|\W)@(\w+)/g, '$1<a href="http://twitter.com/$2">@$2</a>')
               .replace(/(^|\W)#(\w+)/g, '$1#<a href="http://search.twitter.com/search?q=%23$2">$2</a>');
}
