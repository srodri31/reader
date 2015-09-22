$(document).ready(function(){
	var isIn;
	var identi=1;
	var ele=1;
	$(".ui-draggable").draggable({
		revert: false,
		start: function(event, ui) {		
			ui.helper.data('dropped', false);      	
			}	
					
	});
	
	$("#arrastrable"+identi).draggable({	
		revert: false,
		stop: function(event, ui){
			isIn = ui.helper.data('dropped');
			 
		}		
	});
	
	
	
	
	IsIn(isIn);
$("#elemento"+ele).draggable();
/*
	$("#arrastrable2").draggable();
	$("#arrastrable3").draggable();
	$("#arrastrable4").draggable();
	$("#arrastrable5").draggable();
	$("#arrastrable6").draggable();
	$("#arrastrable7").draggable();
	$("#arrastrable8").draggable();
*/
	
	$("#soltable").droppable({
		tolerance: "fit",
	   
		accept: '*',
		 
		drop: function( event, ui ) {
			$(this).html("Lo soltaste!!!");

       var id = ui.draggable.attr("id");
       
        if(id=="arrastrable"+identi){
               ++identi;			      
			      var imagen = document.createElement("img"); 
               imagen.setAttribute("src", "img/piano3.png");
               imagen.setAttribute("id", "arrastrable"+identi);
               
               
               $(imagen).draggable();             
               var div = document.getElementById("hola");
					imagen.style.position="absolute";               
               imagen.style.left="14px";              
               imagen.style.top="0px";
					imagen.style.zIndex="0";               
               div.appendChild(imagen);
               	
         }  	
         
         	if(id=="elemento"+ele){
               ++ele;			      
			      var imagen1 = document.createElement("img"); 
               imagen1.setAttribute("src", "img/piano6.png");
               imagen1.setAttribute("id", "elemento"+ele);
              
               $(imagen1).draggable();             
               var div1 = document.getElementById("hola1");
					imagen1.style.position="absolute";               
               imagen1.style.left="15px";              
               imagen1.style.top="0px";
					imagen1.style.zIndex="0";               
               div1.appendChild(imagen1);
               
         }
        	
         
			
		
			ui.draggable.data('dropped', true);
			$(this).addClass("ui-state-highlight").find("img").html("Dropped!");
			var currentDraggable = $(ui.draggable).attr('id');
		
			 
         
         
		},
		out: function( event, ui ) {
			//window.alert("Afuera no idiota!!!");
			var currentDraggable = $(ui.draggable).attr('id');
			var elem = document.getElementById(currentDraggable);
			elem.parentNode.removeChild(elem);
			
		}
	});
	
	$("#soltable").droppable("option", "activeClass", "sueltaaqui");
	
	function IsIn(){
		if(!isIn){
			$(".ui-draggable").draggable({
				revert:  function(dropped) {			   
				   return !dropped;
				} 
			})
		}
	}
	
})		