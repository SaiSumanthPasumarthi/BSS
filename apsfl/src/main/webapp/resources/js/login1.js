function copyrightPos() {
    var windowHeight = $(window).height();
    if (windowHeight < 700) {
        $('.account-copyright').css('position', 'relative').css('margin-top', 40);
    } else {
        $('.account-copyright').css('position', '').css('margin-top', '');
    }
}

$(window).resize(function() {
    copyrightPos();
});

$(function() {

    copyrightPos();
    if ($('body').data('page') == 'login') {

        /* Show / Hide Password Recover Form */
        $('#password').on('click', function(e) {
            e.preventDefault();
            $('.form-signin').slideUp(300, function() {
                $('.form-password').slideDown(300);
            });
        });
        $('#login').on('click', function(e) {
            e.preventDefault();
            $('.form-password').slideUp(300, function() {
                $('.form-signin').slideDown(300);
            });
        });

        var form = $(".form-signin");
        $('#submit-form').click(function(e) {
            form.validate({
                rules: {
                    username: {
                        required: true,
                        minlength: 3,
                    },
                    password: {
                        required: true,
                        minlength: 6,
                        maxlength: 16
                    }
                },
                messages: {
                    username: {
                        required: 'Enter your username'
                    },
                    password: {
                        required: 'Write your password'
                    }
                },
                errorPlacement: function(error, element) {
                    error.insertAfter(element);
                }
            });
            e.preventDefault();
/*            if (form.valid()) {
                $(this).addClass('ladda-button');
                var l = Ladda.create(this);
                l.start();
                setTimeout(function() {
                    window.location.href = "dashboard.html";
                }, 2000);
            } else {
                $('body').addClass('boxed');
                // alert('not valid');
            }*/
        });

        /*
        $('#submit-form, #submit-password').click(function() {

            e.preventDefault();
            var l = Ladda.create(this);
            l.start();
            setTimeout(function() {
                window.location.href = "dashboard.html";
            }, 2000);
        });
        */
		
		
       /* $.backstretch(["images/gallery/login2.jpg"], {
            fade: 600,
            duration: 4000
        });*/
	// this abobe code is for bakcground image
		
		 //$('body').addClass('boxed');
		 // this is add or remove class


        /***** DEMO CONTENT, CAN BE REMOVED ******/
        $("#account-builder").on('mouseenter', function() {
            TweenMax.to($(this), 0.35, {
                css: {
                    height: 160,
                    width: 500,
                    left: '37%',
                    'border-bottom-left-radius': 0,
                    'border-top-right-radius': 0,
                    '-moz-border-bottom-left-radius': 0,
                    '-moz-border-top-right-radius': 0,
                    '-webkit-border-bottom-left-radius': 0,
                    '-webkit-border-top-right-radius': 0
                },
                ease: Circ.easeInOut
            });
        });
        $("#account-builder").on('mouseleave', function() {
            TweenMax.to($(this), 0.35, {
                css: {
                    height: 44,
                    width: 250,
                    left: '44%',
                    'border-bottom-left-radius': 20,
                    'border-top-right-radius': 20
                },
                ease: Circ.easeInOut
            });
        });
        /* Hide / Show Social Connect */
        $('#social-cb').change(function() {
            if ($(this).is(":checked")) {
                $('.social-btn').slideDown(function() {
                    $('body').removeClass('no-social');
                });
            } else {
                $('.social-btn').slideUp(function() {
                    $('body').addClass('no-social');
                });
            }
        });
        /* Hide / Show Boxed Form */
        $('#boxed-cb').change(function() {
            if ($(this).is(":checked")) {
                TweenMax.to($('.account-wall'), 0.5, {
                    backgroundColor: 'rgba(255, 255, 255,1)',
                    ease: Circ.easeInOut,
                    onComplete: function() {
                        $('body').addClass('boxed');
                    }
                });
            } else {
                TweenMax.to($('.account-wall'), 0.5, {
                    backgroundColor: 'rgba(255, 255, 255,0)',
                    ease: Circ.easeInOut,
                    onComplete: function() {
                        $('body').removeClass('boxed');
                    }
                });
            }
        });
        /* Hide / Show Background Image */
        $('#image-cb').change(function() {
            if ($(this).is(":checked")) {
                $.backstretch(["images/gallery/login1.jpg"], {
                    fade: 600,
                    duration: 4000
                });
                $('#slide-cb').attr('checked', false);
            } else $.backstretch("destroy");
        });
        /* Add / Remove Slide Image */
        $('#slide-cb').change(function() {
            if ($(this).is(":checked")) {
                $.backstretch(["images/gallery/login1.jpg", "images/gallery/login2.jpg", "images/gallery/login3.jpg"], {
                    fade: 600,
                    duration: 4000
                });
                $('#image-cb').attr('checked', false);
            } else {
                $.backstretch("destroy");
            }
        });
        /* Separate Inputs */
        $('#input-cb').change(function() {
            if ($(this).is(":checked")) {
                TweenMax.to($('.username'), 0.3, {
                    css: {
                        marginBottom: 8,
                        'border-bottom-left-radius': 2,
                        'border-bottom-right-radius': 2
                    },
                    ease: Circ.easeInOut,
                    onComplete: function() {
                        $('body').addClass('separate-inputs');
                    }
                });
                TweenMax.to($('.password'), 0.3, {
                    css: {
                        'border-top-left-radius': 2,
                        'border-top-right-radius': 2
                    },
                    ease: Circ.easeInOut
                });
            } else {
                TweenMax.to($('.username'), 0.3, {
                    css: {
                        marginBottom: -1,
                        'border-bottom-left-radius': 0,
                        'border-bottom-right-radius': 0
                    },
                    ease: Circ.easeInOut,
                    onComplete: function() {
                        $('body').removeClass('separate-inputs');
                    }
                });
                TweenMax.to($('.password'), 0.3, {
                    css: {
                        'border-top-left-radius': 0,
                        'border-top-right-radius': 0
                    },
                    ease: Circ.easeInOut
                });
            }
        });
        /* Hide / Show User Image */
        $('#user-cb').change(function() {
            if ($(this).is(":checked")) {
                TweenMax.to($('.user-img'), 0.3, {
                    opacity: 0,
                    ease: Circ.easeInOut
                });
            } else {
                TweenMax.to($('.user-img'), 0.3, {
                    opacity: 1,
                    ease: Circ.easeInOut
                });
            }
        });

    }
    if ($('body').data('page') == 'signup') {

        var form = $(".form-signup");
        $.backstretch(["images/gallery/login1.jpg"], {
            fade: 600,
            duration: 4000
        });
        $("#account-builder").on('mouseenter', function() {
            TweenMax.to($(this), 0.35, {
                css: {
                    height: 160,
                    width: 500,
                    left: '37%',
                    'border-bottom-left-radius': 0,
                    'border-top-right-radius': 0,
                    '-moz-border-bottom-left-radius': 0,
                    '-moz-border-top-right-radius': 0,
                    '-webkit-border-bottom-left-radius': 0,
                    '-webkit-border-top-right-radius': 0
                },
                ease: Circ.easeInOut
            });
        });
        $("#account-builder").on('mouseleave', function() {
            TweenMax.to($(this), 0.35, {
                css: {
                    height: 44,
                    width: 250,
                    left: '44%',
                    'border-bottom-left-radius': 20,
                    'border-top-right-radius': 20
                },
                ease: Circ.easeInOut
            });
        });
        /* Hide / Show Social Connect */
        $('#social-cb').change(function() {
            if ($(this).is(":checked")) {
                $('.social-btn').slideDown(function() {
                    $('body').removeClass('no-social');
                });
            } else {
                $('.social-btn').slideUp(function() {
                    $('body').addClass('no-social');
                });
            }
        });
        /* Hide / Show Boxed Form */
        $('#boxed-cb').change(function() {
            if ($(this).is(":checked")) {
                TweenMax.to($('.account-wall'), 0.5, {
                    backgroundColor: 'rgba(255, 255, 255,1)',
                    ease: Circ.easeInOut,
                    onComplete: function() {
                        $('body').addClass('boxed');
                    }
                });
            } else {
                TweenMax.to($('.account-wall'), 0.5, {
                    backgroundColor: 'rgba(255, 255, 255,0)',
                    ease: Circ.easeInOut,
                    onComplete: function() {
                        $('body').removeClass('boxed');
                    }
                });
            }
        });
        /* Hide / Show Background Image */
        $('#image-cb').change(function() {
            if ($(this).is(":checked")) {
                $.backstretch(["../assets/global/images/gallery/login.jpg"], {
                    fade: 600,
                    duration: 4000
                });
                $('#slide-cb').attr('checked', false);
            } else $.backstretch("destroy");
        });
        /* Add / Remove Slide Image */
        $('#slide-cb').change(function() {
            if ($(this).is(":checked")) {
                $.backstretch(["images/gallery/login1.jpg", "images/gallery/login2.jpg", "images/gallery/login3.jpg"], {
                    fade: 600,
                    duration: 4000
                });
                $('#image-cb').attr('checked', false);
            } else {
                $.backstretch("destroy");
            }
        });
        /* Hide / Show Terms Checkbox */
        $('#terms-cb').change(function() {
            if ($(this).is(":checked")) {
                $('.terms').slideDown(function() {
                    $('body').removeClass('no-terms');
                });
            } else {
                $('.terms').slideUp(function() {
                    $('body').addClass('no-terms');
                });
            }
        });
        /* Hide / Show User Image */
        $('#user-cb').change(function() {
            if ($(this).is(":checked")) {
                TweenMax.to($('.user-img'), 0.3, {
                    opacity: 0,
                    ease: Circ.easeInOut
                });
            } else {
                TweenMax.to($('.user-img'), 0.3, {
                    opacity: 1,
                    ease: Circ.easeInOut
                });
            }
        });
        $('#submit-form').click(function(e) {
            form.validate({
                rules: {
                    firstname: {
                        required: true,
                        minlength: 3,
                    },
                    lastname: {
                        required: true,
                        minlength: 4,
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    password: {
                        required: true,
                        minlength: 6,
                        maxlength: 16
                    },
                    password2: {
                        required: true,
                        minlength: 6,
                        maxlength: 16,
                        equalTo: '#password'
                    },
                    terms: {
                        required: true
                    }
                },
                messages: {
                    firstname: {
                        required: 'Enter your first name',
                        minlength: 'Enter at least 3 characters or more'
                    },
                    lastname: {
                        required: 'Enter your last name',
                        minlength: 'Enter at least 3 characters or more'
                    },
                    email: {
                        required: 'Enter email address',
                        email: 'Enter a valid email address'
                    },
                    password: {
                        required: 'Write your password',
                        minlength: 'Minimum 6 characters',
                        maxlength: 'Maximum 16 characters'
                    },
                    password2: {
                        required: 'Write your password',
                        minlength: 'Minimum 6 characters',
                        maxlength: 'Maximum 16 characters',
                        equalTo: '2 passwords must be the same'
                    },
                    terms: {
                        required: 'You must agree with terms'
                    }
                },
                errorPlacement: function(error, element) {
                    if (element.is(":radio") || element.is(":checkbox")) {
                        element.closest('.option-group').after(error);
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
            e.preventDefault();
            if (form.valid()) {
                $(this).addClass('ladda-button');
                alert('valide');
                var l = Ladda.create(this);
                l.start();
                setTimeout(function() {
                    window.location.href = "dashboard.html";
                }, 2000);
            } else {
                // alert('not valid');
            }
        });

    }
});
/* Spin */
!function(a,b){"object"==typeof exports?module.exports=b():"function"==typeof define&&define.amd?define(b):a.Spinner=b()}(this,function(){"use strict";function a(a,b){var c,d=document.createElement(a||"div");for(c in b)d[c]=b[c];return d}function b(a){for(var b=1,c=arguments.length;c>b;b++)a.appendChild(arguments[b]);return a}function c(a,b,c,d){var e=["opacity",b,~~(100*a),c,d].join("-"),f=.01+c/d*100,g=Math.max(1-(1-a)/b*(100-f),a),h=j.substring(0,j.indexOf("Animation")).toLowerCase(),i=h&&"-"+h+"-"||"";return l[e]||(m.insertRule("@"+i+"keyframes "+e+"{0%{opacity:"+g+"}"+f+"%{opacity:"+a+"}"+(f+.01)+"%{opacity:1}"+(f+b)%100+"%{opacity:"+a+"}100%{opacity:"+g+"}}",m.cssRules.length),l[e]=1),e}function d(a,b){var c,d,e=a.style;if(void 0!==e[b])return b;for(b=b.charAt(0).toUpperCase()+b.slice(1),d=0;d<k.length;d++)if(c=k[d]+b,void 0!==e[c])return c}function e(a,b){for(var c in b)a.style[d(a,c)||c]=b[c];return a}function f(a){for(var b=1;b<arguments.length;b++){var c=arguments[b];for(var d in c)void 0===a[d]&&(a[d]=c[d])}return a}function g(a){for(var b={x:a.offsetLeft,y:a.offsetTop};a=a.offsetParent;)b.x+=a.offsetLeft,b.y+=a.offsetTop;return b}function h(a){return"undefined"==typeof this?new h(a):(this.opts=f(a||{},h.defaults,n),void 0)}function i(){function c(b,c){return a("<"+b+' xmlns="urn:schemas-microsoft.com:vml" class="spin-vml">',c)}m.addRule(".spin-vml","behavior:url(#default#VML)"),h.prototype.lines=function(a,d){function f(){return e(c("group",{coordsize:j+" "+j,coordorigin:-i+" "+-i}),{width:j,height:j})}function g(a,g,h){b(l,b(e(f(),{rotation:360/d.lines*a+"deg",left:~~g}),b(e(c("roundrect",{arcsize:d.corners}),{width:i,height:d.width,left:d.radius,top:-d.width>>1,filter:h}),c("fill",{color:d.color,opacity:d.opacity}),c("stroke",{opacity:0}))))}var h,i=d.length+d.width,j=2*i,k=2*-(d.width+d.length)+"px",l=e(f(),{position:"absolute",top:k,left:k});if(d.shadow)for(h=1;h<=d.lines;h++)g(h,-2,"progid:DXImageTransform.Microsoft.Blur(pixelradius=2,makeshadow=1,shadowopacity=.3)");for(h=1;h<=d.lines;h++)g(h);return b(a,l)},h.prototype.opacity=function(a,b,c,d){var e=a.firstChild;d=d.shadow&&d.lines||0,e&&b+d<e.childNodes.length&&(e=e.childNodes[b+d],e=e&&e.firstChild,e=e&&e.firstChild,e&&(e.opacity=c))}}var j,k=["webkit","Moz","ms","O"],l={},m=function(){var c=a("style",{type:"text/css"});return b(document.getElementsByTagName("head")[0],c),c.sheet||c.styleSheet}(),n={lines:12,length:7,width:5,radius:10,rotate:0,corners:1,color:"#000",direction:1,speed:1,trail:100,opacity:.25,fps:20,zIndex:2e9,className:"spinner",top:"auto",left:"auto",position:"relative"};h.defaults={},f(h.prototype,{spin:function(b){this.stop();var c,d,f=this,h=f.opts,i=f.el=e(a(0,{className:h.className}),{position:h.position,width:0,zIndex:h.zIndex}),k=h.radius+h.length+h.width;if(b&&(b.insertBefore(i,b.firstChild||null),d=g(b),c=g(i),e(i,{left:("auto"==h.left?d.x-c.x+(b.offsetWidth>>1):parseInt(h.left,10)+k)+"px",top:("auto"==h.top?d.y-c.y+(b.offsetHeight>>1):parseInt(h.top,10)+k)+"px"})),i.setAttribute("role","progressbar"),f.lines(i,f.opts),!j){var l,m=0,n=(h.lines-1)*(1-h.direction)/2,o=h.fps,p=o/h.speed,q=(1-h.opacity)/(p*h.trail/100),r=p/h.lines;!function s(){m++;for(var a=0;a<h.lines;a++)l=Math.max(1-(m+(h.lines-a)*r)%p*q,h.opacity),f.opacity(i,a*h.direction+n,l,h);f.timeout=f.el&&setTimeout(s,~~(1e3/o))}()}return f},stop:function(){var a=this.el;return a&&(clearTimeout(this.timeout),a.parentNode&&a.parentNode.removeChild(a),this.el=void 0),this},lines:function(d,f){function g(b,c){return e(a(),{position:"absolute",width:f.length+f.width+"px",height:f.width+"px",background:b,boxShadow:c,transformOrigin:"left",transform:"rotate("+~~(360/f.lines*i+f.rotate)+"deg) translate("+f.radius+"px,0)",borderRadius:(f.corners*f.width>>1)+"px"})}for(var h,i=0,k=(f.lines-1)*(1-f.direction)/2;i<f.lines;i++)h=e(a(),{position:"absolute",top:1+~(f.width/2)+"px",transform:f.hwaccel?"translate3d(0,0,0)":"",opacity:f.opacity,animation:j&&c(f.opacity,f.trail,k+i*f.direction,f.lines)+" "+1/f.speed+"s linear infinite"}),f.shadow&&b(h,e(g("#000","0 0 4px #000"),{top:"2px"})),b(d,b(h,g(f.color,"0 0 1px rgba(0,0,0,.1)")));return d},opacity:function(a,b,c){b<a.childNodes.length&&(a.childNodes[b].style.opacity=c)}});var o=e(a("group"),{behavior:"url(#default#VML)"});return!d(o,"transform")&&o.adj?i():j=d(o,"animation"),h});

/* Lada 
https://github.com/hakimel/Ladda
*/
!function(a,b){"object"==typeof exports?module.exports=b():"function"==typeof define&&define.amd?define(["spin"],b):a.Ladda=b(a.Spinner)}(this,function(a){"use strict";function b(a){if("undefined"==typeof a)return console.warn("Ladda button target must be defined."),void 0;a.querySelector(".ladda-label")||(a.innerHTML='<span class="ladda-label">'+a.innerHTML+"</span>");var b=e(a),c=document.createElement("span");c.className="ladda-spinner",a.appendChild(c);var d,f={start:function(){return a.setAttribute("disabled",""),a.setAttribute("data-loading",""),clearTimeout(d),b.spin(c),this.setProgress(0),this},startAfter:function(a){return clearTimeout(d),d=setTimeout(function(){f.start()},a),this},stop:function(){return a.removeAttribute("disabled"),a.removeAttribute("data-loading"),clearTimeout(d),d=setTimeout(function(){b.stop()},1e3),this},toggle:function(){return this.isLoading()?this.stop():this.start(),this},setProgress:function(b){b=Math.max(Math.min(b,1),0);var c=a.querySelector(".ladda-progress");0===b&&c&&c.parentNode?c.parentNode.removeChild(c):(c||(c=document.createElement("div"),c.className="ladda-progress",a.appendChild(c)),c.style.width=(b||0)*a.offsetWidth+"px")},enable:function(){return this.stop(),this},disable:function(){return this.stop(),a.setAttribute("disabled",""),this},isLoading:function(){return a.hasAttribute("data-loading")}};return g.push(f),f}function c(a,c){c=c||{};var d=[];"string"==typeof a?d=f(document.querySelectorAll(a)):"object"==typeof a&&"string"==typeof a.nodeName&&(d=[a]);for(var e=0,g=d.length;g>e;e++)!function(){var a=d[e];if("function"==typeof a.addEventListener){var f=b(a),g=-1;a.addEventListener("click",function(){f.startAfter(1),"number"==typeof c.timeout&&(clearTimeout(g),g=setTimeout(f.stop,c.timeout)),"function"==typeof c.callback&&c.callback.apply(null,[f])},!1)}}()}function d(){for(var a=0,b=g.length;b>a;a++)g[a].stop()}function e(b){var c,d=b.offsetHeight;d>32&&(d*=.8),b.hasAttribute("data-spinner-size")&&(d=parseInt(b.getAttribute("data-spinner-size"),10)),b.hasAttribute("data-spinner-color")&&(c=b.getAttribute("data-spinner-color"));var e=12,f=.2*d,g=.6*f,h=7>f?2:3;return new a({color:c||"#fff",lines:e,radius:f,length:g,width:h,zIndex:"auto",top:"auto",left:"auto",className:""})}function f(a){for(var b=[],c=0;c<a.length;c++)b.push(a[c]);return b}var g=[];return{bind:c,create:b,stopAll:d}});