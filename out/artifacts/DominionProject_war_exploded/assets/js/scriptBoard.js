$(document).ready(function () {
    console.log("voor werkt");
    setBoard();
    console.log("na werkt");
    updateActionCardBoard();
    updateVictoryCardBoard();
    updateTreasureCardBoard();
    updateCoinsActionsBuys();
});


function setBoard() {
    console.log("set board werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'getCards'
        }
    });

    request.done(function (data) {
        //$('#player_one_name').html(data.name1);
        //$('#player_two_name').html(data.name2);
        console.log(data);
        console.log(data.CardNames);
        var obj = JSON.parse(data);
        console.log(obj.CardNames);
        generateVisualCardNames(obj.CardNames);


    });
}
function generateActionCardsOnBoard(array) {
    for (var i = 0; i < array.length; i++) {
        var html = '<li>';
        var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
        html += '<p class="counteronactioncards">' + array[i].amount + '</p>';
        html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
        html += '<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">'
        html += '</li>';
        $(".actioncards_on_table_print").append(html);
    }
}
function generateVictoryCardsOnBoard(array) {
    for (var i = 0; i < array.length; i++) {
        var html = '<li>';
        var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
        html += '<p class="counteronsmallcards">' + array[i].amount + '</p>';
        html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
        html += '<img alt="buy '+ array[i].name.toLowerCase() +'" title="buy '+ array[i].name.toLowerCase() +'" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">'
        html += '</li>';
        $("#victory_cards ul").append(html);
    }
}
function generateMoneyCardsOnBoard(array) {
    for (var i = 0; i < array.length; i++) {
        var html = '<li>';
        var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
        html += '<p class="counteronsmallcards">' + array[i].amount + '</p>';
        html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
        html += '<img alt="buy '+ array[i].name.toLowerCase() +'" title="buy '+ array[i].name.toLowerCase() +'" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">'
        html += '</li>';
        $("#money_cards ul").append(html);
    }
}

function updateActionCardBoard() {

    console.log("update action werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updateActionBoard'

        }
    });

    request.done(function (data) {
        //$('#player_one_name').html(data.name1);
        //$('#player_two_name').html(data.name2);
        console.log(data);
        var obj = JSON.parse(data);
        console.log(obj.actionCardsOnBoard);
        generateActionCardsOnBoard(obj.actionCardsOnBoard);
        $("#actioncards_on_table ul li img:nth-of-type(1)").click(function () {
            console.log("gevonden");
            $actioncardOnTableName = $(this).attr("title");
            var src = "<img src='assets/images/Big%20cards/" + $actioncardOnTableName + ".jpg' title = '" + $actioncardOnTableName + "' alt = '" + $actioncardOnTableName + "'/><br>";
            console.log(src);
            $('#bigCard').html(src).css('visibility', 'visible');
        });
        $('#bigCard').click(function () {
            $('#bigCard').css('visibility', 'hidden');
        });
        $("#actioncards_on_table ul li img:nth-of-type(2)").click(function () {
            console.log("kopen werkt");
            buyActionCard();})
        

    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

    //get names
    var requestNames = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {action: 'getNames'}
    });

    requestNames.done(function (data) {
        var obj = JSON.parse(data);
        if (obj.amount == "2"){
            $("#header").html("<div id='two_players'><p id='player_one_name' class='player_one_name'>" + obj.name1 + "</p><p id='player_two_name' class='player_two_name'>" + obj.name2 + "</p></div>");
        } else if (obj.amount == "3"){
            $('#header').html("<div id='three_players'><div class='player_one_name'><p>" + obj.name1 + "</p></div><div class='player_two_name'><p>" + obj.name2 + "</p></div><div class='player_third_name'><p>" + obj.name3 + "</p></div>");
        } else if (obj.amount == "4"){
            $('#header').html("<div id='four_players'><div class='player_one_name'><p>" + obj.name1 + "</p></div><div class='player_two_name'><p>" + obj.name2 + "</p></div> <div class='player_third_name'><p>" + obj.name3 + "</p></div><div class='player_fourth_name'><p>" + obj.name4 + "</p></div></div>");
        }
    });
    requestNames.fail(function (jqXHR, textStatus) {
        alert(jqXHR.status + ' ' + textStatus);
    });

}
function updateVictoryCardBoard(){
    console.log("update victory werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updateVictoryBoard'

        }
    });

    request.done(function (data) {
        console.log(data);
        var obj = JSON.parse(data);
        console.log(obj.victoryCardsOnBoard);
        generateVictoryCardsOnBoard(obj.victoryCardsOnBoard);
    });
        
    request.fail(function (jqXHR, textStatus) {

            alert(jqXHR.status + ' ' + textStatus);
        });

    }

function updateTreasureCardBoard(){
    console.log("update victory werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updateTreasureBoard'

        }
    });

    request.done(function (data) {
        console.log(data);
        var obj = JSON.parse(data);
        console.log(obj.treasureCardsOnBoard);
        generateMoneyCardsOnBoard(obj.treasureCardsOnBoard);
    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

}









// var buyCard = function (event) {
//
//     var buyThisCard = event.target.id;
//     var request = $.ajax({cache: false,
//         dataType: "text",
//         url: "/BoardServlet",
//         data: {action: "buyActionCard",
//             positionOnBoard: buyThisCard}
//     });
//     request.done(function (data) {
//         var obj = JSON.parse(data);
//
//         thisCard = obj.thisCard;
//         console.log(thisCard);
//         amount = obj.amount;
//         coins = obj.coins;
//         buys = obj.buys;
//         actions = obj.actions;
//         amount = obj.amount;
//         $(".coins").html($(".coins").html().replace(parseInt($(".coins").text().split(":")[1]), coins));
//         $(".buys").html($(".buys").html().replace(parseInt($(".buys").text().split(":")[1]), buys));
//         $(".actions").html($(".actions").html().replace(parseInt($(".actions").text().split(":")[1]), actions));
//         if (thisCard < 2) {nextPlayer();}
//         updateAmount(thisCard, amount);
//         ;
//     });
//     request.fail(function (jqXHR, textStatus) {
//         alert(jqXHR.status + ' ' + textStatus);
//     });
// };





function buyActionCard(){
        console.log("kaart spelen werkt");
        var request = $.ajax({ cache: false,
            url: "/BoardServlet",
            type: "GET",
            data:{ action: 'buyActionCard',
                positionOnBoard: $('#actioncards_on_table li').index(this) //TODO dylan index van 'li' moet door gegeven worden als ik + druk, gwn achter deze positionOnBoard zetten
            }
        });
        request.done(function (data) {
            alert(success(data));
        });
        request.fail(function (jqXHR, textStatus) {
            console.log("nie gelukt");
            alert(jqXHR.status + ' ' + textStatus);
        });



}

function updateCoinsActionsBuys(){
    console.log("update coins werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updateCoinsActionsBuys'

        }
    });

    request.done(function (data) {
        console.log(data);
        var obj = JSON.parse(data);
        console.log(obj.CAB);
    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

}


function update() {
                updateHand();
            }

            function updateHand() {
                console.log("updateHand werkt");
                var request = $.ajax({
                    cache: false,
                    url: "/BoardServlet",
                    type: "GET",
                    dataType: "text",
                    data: {
                        action: 'updateHand'

                    }
                });

                request.done(function (data) {
                    //$('#player_one_name').html(data.name1);
                    //$('#player_two_name').html(data.name2);
                    console.log(data);
                    console.log(data.CardNames);
                    var obj = JSON.parse(data);
                    console.log(obj.CardNames);
                    generateVisualCardNames(obj.CardNames);
                    $(function() {

                        var $el = $( '#baraja-el' ),
                            baraja = $el.baraja();

                        // playing with different origins and ranges
                        $( '#opencards' ).on('click', function(event) {
                            console.log("test")
                            baraja.fan( {
                                speed : 500,
                                easing : 'ease-out',
                                range : 45,
                                direction : 'middle',
                                origin : { x : 50, y : 200 },
                                center : true,
                            } );
                            console.log("test2")
                        } );
                        $( '#closecards' ).on( 'click', function( event ) {
                            baraja.close();

                        } );
                    });
                    jQuery.fn.reverse = [].reverse;

                    ;( function( $, window, undefined ) {

                        'use strict';

                        // global
                        var Modernizr = window.Modernizr;

                        $.Baraja = function( options, element ) {

                            this.$el = $( element );
                            this._init( options );

                        };

                        // the options
                        $.Baraja.defaults = {
                            // if we want to specify a selector that triggers the next() function. example: '#baraja-nav-next'
                            nextEl : '',
                            // if we want to specify a selector that triggers the previous() function
                            prevEl : '',
                            // default transition speed
                            speed : 300,
                            // default transition easing
                            easing : 'ease-in-out'
                        };

                        $.Baraja.prototype = {

                            _init : function( options ) {

                                // options
                                this.options = $.extend( true, {}, $.Baraja.defaults, options );

                                var transEndEventNames = {
                                    'WebkitTransition' : 'webkitTransitionEnd',
                                    'MozTransition' : 'transitionend',
                                    'OTransition' : 'oTransitionEnd',
                                    'msTransition' : 'MSTransitionEnd',
                                    'transition' : 'transitionend'
                                };
                                this.transEndEventName = transEndEventNames[ Modernizr.prefixed( 'transition' ) ];

                                this._setDefaultFanSettings();

                                this.$items = this.$el.children( 'li' );
                                this.itemsCount = this.$items.length;
                                if( this.itemsCount === 0 ) {
                                    return false;
                                }
                                // support for CSS Transitions
                                this.supportTransitions = Modernizr.csstransitions;
                                // opened/closed deck
                                this.closed = true;
                                // lowest value for the z-index given to the items
                                this.itemZIndexMin = 1000;
                                // sets the item's z-index value
                                this._setStack();
                                // initialize some events
                                this._initEvents();

                            },
                            _setDefaultFanSettings : function() {

                                this.fanSettings = {
                                    // speed for opening/closing
                                    speed : 500,
                                    // easing for opening/closing
                                    easing : 'ease-out',
                                    // difference/range of possible angles that the items will have
                                    // example: with range:90 and center:false the first item
                                    // will have 0deg and the last one 90deg;
                                    // if center:true, then the first one will have 45deg
                                    // and the last one -45deg; in both cases the difference is 90deg
                                    range : 90,
                                    // this defines the position of the first item
                                    // (to the right, to the left)
                                    // and its angle (clockwise / counterclockwise)
                                    direction : 'right',
                                    // transform origin:
                                    // you can also pass a minX and maxX, meaning the left value
                                    // will vary between minX and maxX
                                    origin : { x : 25, y : 100 },
                                    // additional translation of each item
                                    translation : 0,
                                    // if the cards should be centered after the transform
                                    // is applied
                                    center : true,
                                    // add a random factor to the final transform
                                    scatter : false
                                };

                            },
                            _validateDefaultFanSettings : function( settings ) {

                                if( !settings.origin ) {
                                    settings.origin = this.fanSettings.origin;
                                }
                                else {
                                    settings.origin.x = settings.origin.x || this.fanSettings.origin.x;
                                    settings.origin.y = settings.origin.y || this.fanSettings.origin.y;
                                }
                                settings.speed = settings.speed || this.fanSettings.speed;
                                settings.easing = settings.easing || this.fanSettings.easing;
                                settings.direction = settings.direction || this.fanSettings.direction;
                                settings.range = settings.range || this.fanSettings.range;
                                settings.translation = settings.translation || this.fanSettings.translation;
                                if( settings.center == undefined ) {
                                    settings.center = this.fanSettings.center
                                }
                                if( settings.scatter == undefined ) {
                                    settings.scatter = this.fanSettings.scatter
                                }

                                this.direction = settings.direction;

                                return settings;

                            },
                            _setStack : function( $items ) {

                                var self = this;
                                $items = $items || this.$items;

                                $items.each( function( i ) {

                                    $( this ).css( 'z-index', self.itemZIndexMin + self.itemsCount - 1 - i );

                                } );

                            },
                            _updateStack : function( $el, dir ) {

                                var currZIndex = Number( $el.css( 'z-index' ) ),
                                    newZIndex = dir === 'next' ? this.itemZIndexMin - 1 : this.itemZIndexMin + this.itemsCount,
                                    extra = dir === 'next' ? '+=1' : '-=1';

                                $el.css( 'z-index', newZIndex );

                                this.$items.filter( function() {

                                    var zIdx = Number( $( this ).css( 'z-index' ) ),
                                        cond = dir === 'next' ? zIdx < currZIndex : zIdx > currZIndex

                                    return cond;

                                } ).css( 'z-index', extra );

                            },
                            _initEvents : function() {

                                var self = this;

                                if( this.options.nextEl !== '' ) {

                                    $( this.options.nextEl ).on( 'click.baraja', function() {

                                        self._navigate( 'next' );
                                        return false;

                                    } );

                                }

                                if( this.options.prevEl !== '' ) {

                                    $( this.options.prevEl ).on( 'click.baraja', function() {

                                        self._navigate( 'prev' );
                                        return false;

                                    } );

                                }


                            },
                            _resetTransition : function( $el ) {

                                $el.css( {
                                    '-webkit-transition' : 'none',
                                    '-moz-transition' : 'none',
                                    '-ms-transition' : 'none',
                                    '-o-transition' : 'none',
                                    'transition' : 'none'
                                } );

                            },
                            _setOrigin : function( $el, x, y ) {

                                $el.css( 'transform-origin' , x + '% ' + y + '%' );

                            },
                            _setTransition : function( $el, prop, speed, easing, delay ) {

                                if( !this.supportTransitions ) {
                                    return false;
                                }
                                if( !prop ) {
                                    prop = 'all';
                                }
                                if( !speed ) {
                                    speed = this.options.speed;
                                }
                                if( !easing ) {
                                    easing = this.options.easing;
                                }
                                if( !delay ) {
                                    delay = 0;
                                }

                                var styleCSS = '';

                                prop === 'transform' ?
                                    styleCSS = {
                                        '-webkit-transition' : '-webkit-transform ' + speed + 'ms ' + easing + ' ' + delay + 'ms',
                                        '-moz-transition' : '-moz-transform ' + speed + 'ms ' + easing + ' ' + delay + 'ms',
                                        '-ms-transition' : '-ms-transform ' + speed + 'ms ' + easing + ' ' + delay + 'ms',
                                        '-o-transition' : '-o-transform ' + speed + 'ms ' + easing + ' ' + delay + 'ms',
                                        'transition' : 'transform ' + speed + 'ms ' + easing + ' ' + delay + 'ms'
                                    } :
                                    styleCSS = {
                                        '-webkit-transition' : prop + ' ' + speed + 'ms ' + easing + ' ' + delay + 'ms',
                                        '-moz-transition' : prop + ' ' + speed + 'ms ' + easing + ' ' + delay + 'ms',
                                        '-ms-transition' : prop + ' ' + speed + 'ms ' + easing + ' ' + delay + 'ms',
                                        '-o-transition' : prop + ' ' + speed + 'ms ' + easing + ' ' + delay + 'ms',
                                        'transition' : prop + ' ' + speed + 'ms ' + easing + ' ' + delay + 'ms'
                                    }

                                $el.css( styleCSS );

                            },
                            _applyTransition : function( $el, styleCSS, fncomplete, force ) {

                                if( this.supportTransitions ) {

                                    if( fncomplete ) {

                                        $el.on( this.transEndEventName, fncomplete );

                                        if( force ) {
                                            fncomplete.call();
                                        }

                                    }

                                    setTimeout( function() { $el.css( styleCSS ); }, 25 );

                                }
                                else {

                                    $el.css( styleCSS );

                                    if( fncomplete ) {

                                        fncomplete.call();

                                    }

                                }

                            },
                            __move2front : function( $item ) {

                                this.isAnimating = true;

                                var self = this,
                                    isTop = Number( $item.css( 'z-index' ) ) === this.itemZIndexMin + this.itemsCount - 1,
                                    callback = isTop ? function() { self.isAnimating = false; } : function() { return false; },
                                    $item = isTop ? null : $item;

                                // if it's the one with higher z-index, just close the baraja
                                if( !this.closed ) {

                                    this._close( callback, $item );

                                }
                                else {

                                    this._fan();

                                }

                                if( isTop ) {
                                    return false;
                                }
                                this._setOrigin( $item, 50, 50 );

                                $item.css( {
                                    opacity : 0,
                                    transform : 'scale(2) translate(100px) rotate(20deg)'
                                } );

                                this._updateStack( $item, 'prev' );

                                setTimeout( function() {

                                    self._setTransition( $item, 'all', self.options.speed, 'ease-in' );
                                    self._applyTransition( $item, { transform : 'none', opacity : 1 }, function() {

                                        $item.off( self.transEndEventName );
                                        self.isAnimating = false;

                                    } );

                                }, this.options.speed / 2 );

                            },
                            _close : function( callback, $item ) {

                                var self = this,
                                    $items = self.$items,
                                    force = this.closed ? true : false;

                                if( $item ) {
                                    $items = $items.not( $item );
                                }

                                this._applyTransition( $items, { transform : 'none' }, function() {

                                    self.closed = true;
                                    $items.off( self.transEndEventName );
                                    self._resetTransition( $items );
                                    setTimeout(function() {

                                        self._setOrigin( $items, 50, 50 );

                                        if( callback ) {
                                            callback.call();
                                        }

                                    }, 25);

                                }, force );

                            },
                            _fan : function( settings ) {

                                var self = this;

                                this.closed = false;

                                settings = this._validateDefaultFanSettings( settings || {} );

                                // set transform origins
                                // if minX and maxX are passed:
                                if( settings.origin.minX && settings.origin.maxX ) {

                                    var max = settings.origin.maxX, min = settings.origin.minX,
                                        stepOrigin = ( max - min ) / this.itemsCount;

                                    this.$items.each( function( i ) {

                                        var $el = $( this ),
                                            pos = self.itemsCount - 1 - ( Number( $el.css( 'z-index' ) ) - self.itemZIndexMin ),
                                            originX = pos * ( max - min + stepOrigin ) / self.itemsCount + min;

                                        if( settings.direction === 'left' ) {

                                            originX = max + min - originX;

                                        }

                                        self._setOrigin( $( this ), originX, settings.origin.y );

                                    } );

                                }
                                else {

                                    this._setOrigin( this.$items, settings.origin.x , settings.origin.y );

                                }

                                this._setTransition( this.$items, 'transform', settings.speed, settings.easing );

                                var stepAngle = settings.range / ( this.itemsCount - 1 ),
                                    stepTranslation = settings.translation / ( this.itemsCount - 1 ),
                                    cnt = 0;

                                this.$items.each( function( i ) {

                                    var $el = $( this ),
                                        pos = self.itemsCount - 1 - ( Number( $el.css( 'z-index' ) ) - self.itemZIndexMin ),
                                        val = settings.center ? settings.range / 2 : settings.range,
                                        angle = val - stepAngle * pos,
                                        position = stepTranslation * ( self.itemsCount - pos - 1 );

                                    if( settings.direction === 'left' ) {

                                        angle *= -1;
                                        position *= -1;

                                    }

                                    if( settings.scatter ) {

                                        var extraAngle = Math.floor( Math.random() * stepAngle ),
                                            extraPosition = Math.floor( Math.random() * stepTranslation ) ;

                                        // not for the first item..
                                        if( pos !== self.itemsCount - 1 ) {

                                            angle = settings.direction === 'left' ? angle + extraAngle : angle - extraAngle;
                                            position = settings.direction === 'left' ? position - extraPosition : position + extraPosition;

                                        }

                                    }

                                    // save..
                                    $el.data( { translation : position, rotation : angle } );

                                    self._applyTransition( $el, { transform : 'translate(' + position + 'px) rotate(' + angle + 'deg)' }, function() {

                                        ++cnt;
                                        $el.off( self.transEndEventName );

                                        if( cnt === self.itemsCount - 1 ) {
                                            self.isAnimating = false;
                                        }

                                    } );

                                } );

                            },
                            // adds new elements to the deck
                            _add : function( $elems ) {

                                var self = this,
                                    newElemsCount = $elems.length, cnt = 0;

                                $elems.css( 'opacity', 0 ).appendTo( this.$el );

                                // reset
                                this.$items = this.$el.children( 'li' );
                                this.itemsCount = this.$items.length;

                                // set z-indexes
                                this._setStack( $elems );

                                // animate new items
                                $elems.css( 'transform', 'scale(1.8) translate(200px) rotate(15deg)' ).reverse().each( function( i ) {

                                    var $el = $( this );

                                    self._setTransition( $el, 'all', 500, 'ease-out', i * 200 );
                                    self._applyTransition( $el, { transform : 'none', opacity : 1 }, function() {

                                        ++cnt;

                                        $el.off( self.transEndEventName );
                                        self._resetTransition( $el );

                                        if( cnt === newElemsCount ) {
                                            self.isAnimating = false;
                                        }

                                    } );

                                } );

                            },
                            _allowAction : function() {

                                return this.itemsCount > 1;

                            },
                            _prepare : function( callback ) {

                                var self = this;

                                if( !this.closed ) {

                                    this._close( function() {

                                        callback.call();

                                    } );

                                }
                                else {

                                    callback.call();

                                }

                            },
                            _dispatch : function( action, args ) {

                                var self = this;

                                if( ( ( action === this._fan || action === this._navigate ) && !this._allowAction() ) || this.isAnimating ) {
                                    return false;
                                }

                                this.isAnimating = true;

                                this._prepare( function() {

                                    action.call( self, args );

                                } );

                            },
                            // public method: closes the deck
                            close : function( settings ) {

                                if( this.isAnimating ) {
                                    return false;
                                }
                                this._close();

                            },
                            // public method: shows next item
                            next : function() {

                                this._dispatch( this._navigate, 'next' );

                            },
                            // public method: shows previous item
                            previous : function() {

                                this._dispatch( this._navigate, 'prev' );

                            },
                            // public method: opens the deck
                            fan : function( settings ) {

                                this._dispatch( this._fan, settings );

                            },
                            // public method: adds new elements
                            add : function ( $elems ) {

                                this._dispatch( this._add, $elems );

                            }

                        };

                        var logError = function( message ) {

                            if ( window.console ) {

                                window.console.error( message );

                            }

                        };

                        $.fn.baraja = function( options ) {

                            var instance = $.data( this, 'baraja' );

                            if ( typeof options === 'string' ) {

                                var args = Array.prototype.slice.call( arguments, 1 );

                                this.each(function() {

                                    if ( !instance ) {

                                        logError( "cannot call methods on baraja prior to initialization; " +
                                            "attempted to call method '" + options + "'" );
                                        return;

                                    }

                                    if ( !$.isFunction( instance[options] ) || options.charAt(0) === "_" ) {

                                        logError( "no such method '" + options + "' for baraja instance" );
                                        return;

                                    }

                                    instance[ options ].apply( instance, args );

                                });

                            }
                            else {

                                this.each(function() {

                                    if ( instance ) {

                                        instance._init();

                                    }
                                    else {

                                        instance = $.data( this, 'baraja', new $.Baraja( options, this ) );

                                    }

                                });

                            }

                            return instance;

                        };

                    } )( jQuery, window );



                });
                request.fail(function (jqXHR, textStatus) {

                    alert(jqXHR.status + ' ' + textStatus);
                });

            }


            function generateVisualCardNames(array) {
                var zIndex = 1000;
                for (var i = 0; i < array.length; i++) {
                    var html = '<li style="z-index: '+ zIndex +' " >';
                    var src = 'assets/images/Big%20cards/' + array[i].toLowerCase() + '.jpg';
                    html += '<img alt="' + array[i].toLowerCase() + '"  title="' + array[i].toLowerCase() + '" src="' + src + '" />';
                    html += '</li>';
                    zIndex ++;
                    $("#baraja-el").append(html);

                }
            }































