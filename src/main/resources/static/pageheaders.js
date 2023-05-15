var PageHeaders=(function(){
    function debounce(func){
      var timer;
      return function(event){
        if(timer){
            clearTimeout(timer);
        }
        timer = setTimeout(func,100,event);
      };
    }
    function doResize(){
        //console.log('resize');
        const pagesWithHeader=document.getElementsByClassName('tk-page-has-header');
        const pagesWithFooter=document.getElementsByClassName('tk-page-has-footer');
        const pagesWithNavLeft=document.getElementsByClassName('tk-page-has-nav-left');
        const pagesWithNavRight=document.getElementsByClassName('tk-page-has-nav-right');
        if(pagesWithHeader.length==0&&pagesWithFooter.length==0){
            return;
        }
        const pages=Array.from(pagesWithHeader)
            .concat(Array.from(pagesWithFooter))
            .concat(Array.from(pagesWithNavLeft))
            .concat(Array.from(pagesWithNavRight));
        const keys=[];
        for(const p in pages){
            var pageId=pages[p].parentElement.id;
            if(keys.includes(pageId)){
                continue;
            }
            keys.push(pageId);
            var headers=document.getElementsByClassName('tk-panel-page-header page-'+pageId);
            var footers=document.getElementsByClassName('tk-panel-page-footer page-'+pageId);
            var navLefts=document.getElementsByClassName('tk-panel-nav-left page-'+pageId);
            var navRights=document.getElementsByClassName('tk-panel-nav-right page-'+pageId);
            var paddingTop=0;
            var paddingBottom=0;
            var paddingLeft=0;
            var paddingRight=0;
            if(headers.length!=0){
                paddingTop=headers[0].offsetHeight;
            }
            if(footers.length!=0){
                paddingBottom=footers[0].offsetHeight;
            }
            if(navLefts.length!=0){
                paddingLeft=navLefts[0].offsetWidth;
            }
            if(navRights.length!=0){
                paddingRight=navRights[0].offsetWidth;
            }
            pages[p].style.paddingTop=paddingTop+'px';
            pages[p].style.paddingBottom=paddingBottom+'px';
            pages[p].style.paddingLeft=paddingLeft+'px';
            pages[p].style.paddingRight=paddingRight+'px';
            if(navLefts.length!=0){
                navLefts[0].style.paddingTop=paddingTop+'px';
                navLefts[0].style.paddingBottom=paddingBottom+'px';
            }
            if(navRights.length!=0){
                navRights[0].style.paddingTop=paddingTop+'px';
                navRights[0].style.paddingBottom=paddingBottom+'px';
            }
        }
    }
    window.addEventListener("resize",debounce(doResize));
    return {
        resize:doResize
    };
})();