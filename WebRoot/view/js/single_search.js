 function single_search_action(type) {
 	if(type == 'movie')
	$('#single_search_bar').attr("action", '${my_domain }/review/draftMovieReview/${movieId }.action');
	//alert($('#comment_action_form').a);
	$('#single_search_bar').submit();
}