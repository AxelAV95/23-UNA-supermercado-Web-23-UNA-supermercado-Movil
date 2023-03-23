const animateCSS = (element, animation, prefix = 'animate__') =>
  // We create a Promise and return it
  new Promise((resolve, reject) => {
    const animationName = `${prefix}${animation}`;
    const node = document.querySelector(element);

    node.classList.add(`${prefix}animated`, animationName);

    // When the animation ends, we clean the classes and resolve the Promise
    function handleAnimationEnd(event) {
      event.stopPropagation();
      node.classList.remove(`${prefix}animated`, animationName);
      resolve('Animation ended');
    }

    node.addEventListener('animationend', handleAnimationEnd, {once: true});
  });


    function cargarNotificacionesNoVistas(vista = ''){
      $.ajax({
       url:"../../business/ordenaction.php",
       method:"POST",
       data:{vista:vista},
       dataType:"json",
       success:function(data)
       {
        $('.ordenesnotificacion').html(data.notification);
        if(data.unseen_notification > 0)
        {
         $('.count').html(data.unseen_notification);
         let bell = new Wad({source : 'dist/audio/notificacion.wav'});
         bell.play();
         bell.stop();
         animateCSS('.count', 'heartBeat');

        
         
       }
     }
   });
    }

    cargarNotificacionesNoVistas();

    $('.drop').on('click', function(){
     

      animateCSS('.count', 'rotateOutUpLeft').then((message) => {
      $('.count').html('');
        cargarNotificacionesNoVistas('true');;
  });
      
      
    
    });

    setInterval(function(){ 

      cargarNotificacionesNoVistas(); 
    }, 30000);