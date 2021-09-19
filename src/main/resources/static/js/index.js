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
		
		$('#removeFile').on('click', 
		function() {
			$('#file').val('');
			$('#sendFile').hide();
		}
		);
		
		$('#formUpload').submit(function(e) {
			$('#successGenerationFile').show();
		});
		
});