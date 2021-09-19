$(document).ready(
	function(){
		$('#sendFile').hide();
		$('#successGenerationFile').hide();
		
		$('#file').change(
			function() {
				if($(this).val()) {
					$('#sendFile').show();	
				}
			}
		);
		
		$('#resetFile').on('click', 
		function() {
			$('#sendFile').hide();
		}
		);
		
		$('#formUpload').submit(function(e) {
			$('#successGenerationFile').show();
		});
		
});