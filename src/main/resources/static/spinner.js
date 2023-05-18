var Spinner=(function(){
    const CONTAINER_STYLE = 'position:relative;display:block;height:20px;padding:0;margin:0;';
    const SPRITE_STYLE = 'position:relative;padding:0;margin:0;top:0;left:0;display:none;border-radius:10px;vertical-align:top;';
    const spinners={};
    const NUMBER_OF_FRAMES = 8;

    function handleEvent(event){
        //console.log('Spinner',event);
        if(event.name=='beginprogress'&&event.widgetId!=null){
            start(event.widgetId);
        }
        if(event.name=='endprogress'){
            stop(event.widgetId);
        }
    }

    function start(id){
        //console.log("start",id);
        if(spinners.hasOwnProperty(id)){
            stop(id);
        }
        spinners[id]={timer:null,frame:-1,parentElement:null,container:null,sprite:null};
        spinners[id].parentElement=document.getElementById(id);
        var container=document.createElement('div');
        container.setAttribute('style',CONTAINER_STYLE);
        var sprite=document.createElement('div');
        sprite.setAttribute('style',SPRITE_STYLE);
        container.append(sprite);
        spinners[id].sprite=sprite;
        spinners[id].container=container;
        spinners[id].parentElement.append(container);
        reset(id);
    }
    function stop(id){
        //console.log("stop",id);
        if(spinners.hasOwnProperty(id)){
            if(spinners[id].timer){
                clearTimeout(spinners[id].timer);
            }
            if(spinners[id].container!=null){
                spinners[id].container.remove();
            }
            delete spinners[id];
        }
    }
    function draw(id){
        if(!spinners.hasOwnProperty(id)){
            return;
        }
        var sp=spinners[id];
        const sz=10;
        const cy=0;
        const cx=Math.floor(sp.container.offsetWidth/2)-sz;
        var x=0;
        var y=0;
        var w=sz;
        var h=sz;
        var init=sp.frame==-1;
        if(init){
            sp.frame=0;
        }
        if(sp.frame==0){
            x=0;
            y=0;
            w=sz;
            h=sz;
        }
        if(sp.frame==1){
            x=0;
            y=0;
            w=sz*2;
            h=sz;
        }
        if(sp.frame==2){
            x=sz;
            y=0;
            w=sz;
            h=sz;
        }
        if(sp.frame==3){
            x=sz;
            y=0;
            w=sz;
            h=sz*2;
        }
        if(sp.frame==4){
            x=sz;
            y=sz;
            w=sz;
            h=sz;
        }
        if(sp.frame==5){
            x=0;
            y=sz;
            w=sz*2;
            h=sz;
        }
        if(sp.frame==6){
            x=0;
            y=sz;
            w=sz;
            h=sz;
        }
        if(sp.frame==7){
            x=0;
            y=0;
            w=sz;
            h=sz*2;
        }
        //console.log('draw',id,sp.frame,x,y);
        sp.sprite.style.left=cx+x+'px';
        sp.sprite.style.top=cy+y+'px';
        sp.sprite.style.width=w+'px';
        sp.sprite.style.height=h+'px';
        if(init){
            sp.sprite.style.display='inline-block';
            sp.sprite.style.transition='left .2s,top .2s,width .2s,height .2s';
        }
        nextFrame(id);
    }
    function reset(id){
        if(!spinners.hasOwnProperty(id)){
            return;
        }
        if(spinners[id].timer){
            clearTimeout(spinners[id].timer);
        }
        spinners[id].frame=-1;
        spinners[id].timer=setTimeout(draw,500,id);
        var color = window?.matchMedia?.('(prefers-color-scheme:dark)')?.matches ? 'white' : 'black';
        spinners[id].sprite.style.backgroundColor=color;
    }
    function nextFrame(id){
        if(!spinners.hasOwnProperty(id)){
            return;
        }
        if(spinners[id].timer){
            clearTimeout(spinners[id].timer);
        }
        spinners[id].frame++;
        if(spinners[id].frame>=NUMBER_OF_FRAMES){
            spinners[id].frame=0;
        }
        spinners[id].timer=setTimeout(draw,500,id);
    }
    return {
        handleEvent:handleEvent
    };
})();