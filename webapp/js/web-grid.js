/*!
 * modernizr v3.3.0
 * Build http://modernizr.com/download?-csscalc-flexbox-flexboxlegacy-flexboxtweener-flexwrap-setclasses-dontmin-cssclassprefix:mod_
 *
 * Copyright (c)
 *  Faruk Ates
 *  Paul Irish
 *  Alex Sexton
 *  Ryan Seddon
 *  Patrick Kettner
 *  Stu Cox
 *  Richard Herrera

 * MIT License
 */

/*
 * Modernizr tests which native CSS3 and HTML5 features are available in the
 * current UA and makes the results available to you in two ways: as properties on
 * a global `Modernizr` object, and as classes on the `<html>` element. This
 * information allows you to progressively enhance your pages with a granular level
 * of control over the experience.
*/

;(function(window, document, undefined){
  var classes = [];
  

  var tests = [];
  

  /**
   *
   * ModernizrProto is the constructor for Modernizr
   *
   * @class
   * @access public
   */

  var ModernizrProto = {
    // The current version, dummy
    _version: '3.3.0',

    // Any settings that don't work as separate modules
    // can go in here as configuration.
    _config: {
      'classPrefix': "mod_",
      'enableClasses': true,
      'enableJSClass': true,
      'usePrefixes': true
    },

    // Queue of tests
    _q: [],

    // Stub these for people who are listening
    on: function(test, cb) {
      // I don't really think people should do this, but we can
      // safe guard it a bit.
      // -- NOTE:: this gets WAY overridden in src/addTest for actual async tests.
      // This is in case people listen to synchronous tests. I would leave it out,
      // but the code to *disallow* sync tests in the real version of this
      // function is actually larger than this.
      var self = this;
      setTimeout(function() {
        cb(self[test]);
      }, 0);
    },

    addTest: function(name, fn, options) {
      tests.push({name: name, fn: fn, options: options});
    },

    addAsyncTest: function(fn) {
      tests.push({name: null, fn: fn});
    }
  };

  

  // Fake some of Object.create so we can force non test results to be non "own" properties.
  var Modernizr = function() {};
  Modernizr.prototype = ModernizrProto;

  // Leak modernizr globally when you `require` it rather than force it here.
  // Overwrite name so constructor name is nicer :D
  Modernizr = new Modernizr();

  

  /**
   * is returns a boolean if the typeof an obj is exactly type.
   *
   * @access private
   * @function is
   * @param {*} obj - A thing we want to check the type of
   * @param {string} type - A string to compare the typeof against
   * @returns {boolean}
   */

  function is(obj, type) {
    return typeof obj === type;
  }
  ;

  /**
   * Run through all tests and detect their support in the current UA.
   *
   * @access private
   */

  function testRunner() {
    var featureNames;
    var feature;
    var aliasIdx;
    var result;
    var nameIdx;
    var featureName;
    var featureNameSplit;

    for (var featureIdx in tests) {
      if (tests.hasOwnProperty(featureIdx)) {
        featureNames = [];
        feature = tests[featureIdx];
        // run the test, throw the return value into the Modernizr,
        // then based on that boolean, define an appropriate className
        // and push it into an array of classes we'll join later.
        //
        // If there is no name, it's an 'async' test that is run,
        // but not directly added to the object. That should
        // be done with a post-run addTest call.
        if (feature.name) {
          featureNames.push(feature.name.toLowerCase());

          if (feature.options && feature.options.aliases && feature.options.aliases.length) {
            // Add all the aliases into the names list
            for (aliasIdx = 0; aliasIdx < feature.options.aliases.length; aliasIdx++) {
              featureNames.push(feature.options.aliases[aliasIdx].toLowerCase());
            }
          }
        }

        // Run the test, or use the raw value if it's not a function
        result = is(feature.fn, 'function') ? feature.fn() : feature.fn;


        // Set each of the names on the Modernizr object
        for (nameIdx = 0; nameIdx < featureNames.length; nameIdx++) {
          featureName = featureNames[nameIdx];
          // Support dot properties as sub tests. We don't do checking to make sure
          // that the implied parent tests have been added. You must call them in
          // order (either in the test, or make the parent test a dependency).
          //
          // Cap it to TWO to make the logic simple and because who needs that kind of subtesting
          // hashtag famous last words
          featureNameSplit = featureName.split('.');

          if (featureNameSplit.length === 1) {
            Modernizr[featureNameSplit[0]] = result;
          } else {
            // cast to a Boolean, if not one already
            /* jshint -W053 */
            if (Modernizr[featureNameSplit[0]] && !(Modernizr[featureNameSplit[0]] instanceof Boolean)) {
              Modernizr[featureNameSplit[0]] = new Boolean(Modernizr[featureNameSplit[0]]);
            }

            Modernizr[featureNameSplit[0]][featureNameSplit[1]] = result;
          }

          classes.push((result ? '' : 'no-') + featureNameSplit.join('-'));
        }
      }
    }
  }
  ;

  /**
   * docElement is a convenience wrapper to grab the root element of the document
   *
   * @access private
   * @returns {HTMLElement|SVGElement} The root element of the document
   */

  var docElement = document.documentElement;
  

  /**
   * A convenience helper to check if the document we are running in is an SVG document
   *
   * @access private
   * @returns {boolean}
   */

  var isSVG = docElement.nodeName.toLowerCase() === 'svg';
  

  /**
   * setClasses takes an array of class names and adds them to the root element
   *
   * @access private
   * @function setClasses
   * @param {string[]} classes - Array of class names
   */

  // Pass in an and array of class names, e.g.:
  //  ['no-webp', 'borderradius', ...]
  function setClasses(classes) {
    var className = docElement.className;
    var classPrefix = Modernizr._config.classPrefix || '';

    if (isSVG) {
      className = className.baseVal;
    }

    // Change `no-js` to `js` (independently of the `enableClasses` option)
    // Handle classPrefix on this too
    if (Modernizr._config.enableJSClass) {
      var reJS = new RegExp('(^|\\s)' + classPrefix + 'no-js(\\s|$)');
      className = className.replace(reJS, '$1' + classPrefix + 'js$2');
    }

    if (Modernizr._config.enableClasses) {
      // Add the new classes
      className += ' ' + classPrefix + classes.join(' ' + classPrefix);
      isSVG ? docElement.className.baseVal = className : docElement.className = className;
    }

  }

  ;

  /**
   * createElement is a convenience wrapper around document.createElement. Since we
   * use createElement all over the place, this allows for (slightly) smaller code
   * as well as abstracting away issues with creating elements in contexts other than
   * HTML documents (e.g. SVG documents).
   *
   * @access private
   * @function createElement
   * @returns {HTMLElement|SVGElement} An HTML or SVG element
   */

  function createElement() {
    if (typeof document.createElement !== 'function') {
      // This is the case in IE7, where the type of createElement is "object".
      // For this reason, we cannot call apply() as Object is not a Function.
      return document.createElement(arguments[0]);
    } else if (isSVG) {
      return document.createElementNS.call(document, 'http://www.w3.org/2000/svg', arguments[0]);
    } else {
      return document.createElement.apply(document, arguments);
    }
  }

  ;

  /**
   * List of property values to set for css tests. See ticket #21
   * http://git.io/vUGl4
   *
   * @memberof Modernizr
   * @name Modernizr._prefixes
   * @optionName Modernizr._prefixes
   * @optionProp prefixes
   * @access public
   * @example
   *
   * Modernizr._prefixes is the internal list of prefixes that we test against
   * inside of things like [prefixed](#modernizr-prefixed) and [prefixedCSS](#-code-modernizr-prefixedcss). It is simply
   * an array of kebab-case vendor prefixes you can use within your code.
   *
   * Some common use cases include
   *
   * Generating all possible prefixed version of a CSS property
   * ```js
   * var rule = Modernizr._prefixes.join('transform: rotate(20deg); ');
   *
   * rule === 'transform: rotate(20deg); webkit-transform: rotate(20deg); moz-transform: rotate(20deg); o-transform: rotate(20deg); ms-transform: rotate(20deg);'
   * ```
   *
   * Generating all possible prefixed version of a CSS value
   * ```js
   * rule = 'display:' +  Modernizr._prefixes.join('flex; display:') + 'flex';
   *
   * rule === 'display:flex; display:-webkit-flex; display:-moz-flex; display:-o-flex; display:-ms-flex; display:flex'
   * ```
   */

  var prefixes = (ModernizrProto._config.usePrefixes ? ' -webkit- -moz- -o- -ms- '.split(' ') : []);

  // expose these for the plugin API. Look in the source for how to join() them against your input
  ModernizrProto._prefixes = prefixes;

  
/*!
{
  "name": "CSS Calc",
  "property": "csscalc",
  "caniuse": "calc",
  "tags": ["css"],
  "builderAliases": ["css_calc"],
  "authors": ["@calvein"]
}
!*/
/* DOC
Method of allowing calculated values for length units. For example:

```css
//lem {
  width: calc(100% - 3em);
}
```
*/

  Modernizr.addTest('csscalc', function() {
    var prop = 'width:';
    var value = 'calc(10px);';
    var el = createElement('a');

    el.style.cssText = prop + prefixes.join(value + prop);

    return !!el.style.length;
  });


  /**
   * If the browsers follow the spec, then they would expose vendor-specific style as:
   *   elem.style.WebkitBorderRadius
   * instead of something like the following, which would be technically incorrect:
   *   elem.style.webkitBorderRadius

   * Webkit ghosts their properties in lowercase but Opera & Moz do not.
   * Microsoft uses a lowercase `ms` instead of the correct `Ms` in IE8+
   *   erik.eae.net/archives/2008/03/10/21.48.10/

   * More here: github.com/Modernizr/Modernizr/issues/issue/21
   *
   * @access private
   * @returns {string} The string representing the vendor-specific style properties
   */

  var omPrefixes = 'Moz O ms Webkit';
  

  var cssomPrefixes = (ModernizrProto._config.usePrefixes ? omPrefixes.split(' ') : []);
  ModernizrProto._cssomPrefixes = cssomPrefixes;
  

  /**
   * List of JavaScript DOM values used for tests
   *
   * @memberof Modernizr
   * @name Modernizr._domPrefixes
   * @optionName Modernizr._domPrefixes
   * @optionProp domPrefixes
   * @access public
   * @example
   *
   * Modernizr._domPrefixes is exactly the same as [_prefixes](#modernizr-_prefixes), but rather
   * than kebab-case properties, all properties are their Capitalized variant
   *
   * ```js
   * Modernizr._domPrefixes === [ "Moz", "O", "ms", "Webkit" ];
   * ```
   */

  var domPrefixes = (ModernizrProto._config.usePrefixes ? omPrefixes.toLowerCase().split(' ') : []);
  ModernizrProto._domPrefixes = domPrefixes;
  


  /**
   * contains checks to see if a string contains another string
   *
   * @access private
   * @function contains
   * @param {string} str - The string we want to check for substrings
   * @param {string} substr - The substring we want to search the first string for
   * @returns {boolean}
   */

  function contains(str, substr) {
    return !!~('' + str).indexOf(substr);
  }

  ;

  /**
   * cssToDOM takes a kebab-case string and converts it to camelCase
   * e.g. box-sizing -> boxSizing
   *
   * @access private
   * @function cssToDOM
   * @param {string} name - String name of kebab-case prop we want to convert
   * @returns {string} The camelCase version of the supplied name
   */

  function cssToDOM(name) {
    return name.replace(/([a-z])-([a-z])/g, function(str, m1, m2) {
      return m1 + m2.toUpperCase();
    }).replace(/^-/, '');
  }
  ;

  /**
   * fnBind is a super small [bind](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Function/bind) polyfill.
   *
   * @access private
   * @function fnBind
   * @param {function} fn - a function you want to change `this` reference to
   * @param {object} that - the `this` you want to call the function with
   * @returns {function} The wrapped version of the supplied function
   */

  function fnBind(fn, that) {
    return function() {
      return fn.apply(that, arguments);
    };
  }

  ;

  /**
   * testDOMProps is a generic DOM property test; if a browser supports
   *   a certain property, it won't return undefined for it.
   *
   * @access private
   * @function testDOMProps
   * @param {array.<string>} props - An array of properties to test for
   * @param {object} obj - An object or Element you want to use to test the parameters again
   * @param {boolean|object} elem - An Element to bind the property lookup again. Use `false` to prevent the check
   */
  function testDOMProps(props, obj, elem) {
    var item;

    for (var i in props) {
      if (props[i] in obj) {

        // return the property name as a string
        if (elem === false) {
          return props[i];
        }

        item = obj[props[i]];

        // let's bind a function
        if (is(item, 'function')) {
          // bind to obj unless overriden
          return fnBind(item, elem || obj);
        }

        // return the unbound function or obj or value
        return item;
      }
    }
    return false;
  }

  ;

  /**
   * Create our "modernizr" element that we do most feature tests on.
   *
   * @access private
   */

  var modElem = {
    elem: createElement('modernizr')
  };

  // Clean up this element
  Modernizr._q.push(function() {
    delete modElem.elem;
  });

  

  var mStyle = {
    style: modElem.elem.style
  };

  // kill ref for gc, must happen before mod.elem is removed, so we unshift on to
  // the front of the queue.
  Modernizr._q.unshift(function() {
    delete mStyle.style;
  });

  

  /**
   * domToCSS takes a camelCase string and converts it to kebab-case
   * e.g. boxSizing -> box-sizing
   *
   * @access private
   * @function domToCSS
   * @param {string} name - String name of camelCase prop we want to convert
   * @returns {string} The kebab-case version of the supplied name
   */

  function domToCSS(name) {
    return name.replace(/([A-Z])/g, function(str, m1) {
      return '-' + m1.toLowerCase();
    }).replace(/^ms-/, '-ms-');
  }
  ;

  /**
   * getBody returns the body of a document, or an element that can stand in for
   * the body if a real body does not exist
   *
   * @access private
   * @function getBody
   * @returns {HTMLElement|SVGElement} Returns the real body of a document, or an
   * artificially created element that stands in for the body
   */

  function getBody() {
    // After page load injecting a fake body doesn't work so check if body exists
    var body = document.body;

    if (!body) {
      // Can't use the real body create a fake one.
      body = createElement(isSVG ? 'svg' : 'body');
      body.fake = true;
    }

    return body;
  }

  ;

  /**
   * injectElementWithStyles injects an element with style element and some CSS rules
   *
   * @access private
   * @function injectElementWithStyles
   * @param {string} rule - String representing a css rule
   * @param {function} callback - A function that is used to test the injected element
   * @param {number} [nodes] - An integer representing the number of additional nodes you want injected
   * @param {string[]} [testnames] - An array of strings that are used as ids for the additional nodes
   * @returns {boolean}
   */

  function injectElementWithStyles(rule, callback, nodes, testnames) {
    var mod = 'modernizr';
    var style;
    var ret;
    var node;
    var docOverflow;
    var div = createElement('div');
    var body = getBody();

    if (parseInt(nodes, 10)) {
      // In order not to give false positives we create a node for each test
      // This also allows the method to scale for unspecified uses
      while (nodes--) {
        node = createElement('div');
        node.id = testnames ? testnames[nodes] : mod + (nodes + 1);
        div.appendChild(node);
      }
    }

    style = createElement('style');
    style.type = 'text/css';
    style.id = 's' + mod;

    // IE6 will false positive on some tests due to the style element inside the test div somehow interfering offsetHeight, so insert it into body or fakebody.
    // Opera will act all quirky when injecting elements in documentElement when page is served as xml, needs fakebody too. #270
    (!body.fake ? div : body).appendChild(style);
    body.appendChild(div);

    if (style.styleSheet) {
      style.styleSheet.cssText = rule;
    } else {
      style.appendChild(document.createTextNode(rule));
    }
    div.id = mod;

    if (body.fake) {
      //avoid crashing IE8, if background image is used
      body.style.background = '';
      //Safari 5.13/5.1.4 OSX stops loading if ::-webkit-scrollbar is used and scrollbars are visible
      body.style.overflow = 'hidden';
      docOverflow = docElement.style.overflow;
      docElement.style.overflow = 'hidden';
      docElement.appendChild(body);
    }

    ret = callback(div, rule);
    // If this is done after page load we don't want to remove the body so check if body exists
    if (body.fake) {
      body.parentNode.removeChild(body);
      docElement.style.overflow = docOverflow;
      // Trigger layout so kinetic scrolling isn't disabled in iOS6+
      docElement.offsetHeight;
    } else {
      div.parentNode.removeChild(div);
    }

    return !!ret;

  }

  ;

  /**
   * nativeTestProps allows for us to use native feature detection functionality if available.
   * some prefixed form, or false, in the case of an unsupported rule
   *
   * @access private
   * @function nativeTestProps
   * @param {array} props - An array of property names
   * @param {string} value - A string representing the value we want to check via @supports
   * @returns {boolean|undefined} A boolean when @supports exists, undefined otherwise
   */

  // Accepts a list of property names and a single value
  // Returns `undefined` if native detection not available
  function nativeTestProps(props, value) {
    var i = props.length;
    // Start with the JS API: http://www.w3.org/TR/css3-conditional/#the-css-interface
    if ('CSS' in window && 'supports' in window.CSS) {
      // Try every prefixed variant of the property
      while (i--) {
        if (window.CSS.supports(domToCSS(props[i]), value)) {
          return true;
        }
      }
      return false;
    }
    // Otherwise fall back to at-rule (for Opera 12.x)
    else if ('CSSSupportsRule' in window) {
      // Build a condition string for every prefixed variant
      var conditionText = [];
      while (i--) {
        conditionText.push('(' + domToCSS(props[i]) + ':' + value + ')');
      }
      conditionText = conditionText.join(' or ');
      return injectElementWithStyles('@supports (' + conditionText + ') { #modernizr { position: absolute; } }', function(node) {
        return getComputedStyle(node, null).position == 'absolute';
      });
    }
    return undefined;
  }
  ;

  // testProps is a generic CSS / DOM property test.

  // In testing support for a given CSS property, it's legit to test:
  //    `elem.style[styleName] !== undefined`
  // If the property is supported it will return an empty string,
  // if unsupported it will return undefined.

  // We'll take advantage of this quick test and skip setting a style
  // on our modernizr element, but instead just testing undefined vs
  // empty string.

  // Property names can be provided in either camelCase or kebab-case.

  function testProps(props, prefixed, value, skipValueTest) {
    skipValueTest = is(skipValueTest, 'undefined') ? false : skipValueTest;

    // Try native detect first
    if (!is(value, 'undefined')) {
      var result = nativeTestProps(props, value);
      if (!is(result, 'undefined')) {
        return result;
      }
    }

    // Otherwise do it properly
    var afterInit, i, propsLength, prop, before;

    // If we don't have a style element, that means we're running async or after
    // the core tests, so we'll need to create our own elements to use

    // inside of an SVG element, in certain browsers, the `style` element is only
    // defined for valid tags. Therefore, if `modernizr` does not have one, we
    // fall back to a less used element and hope for the best.
    var elems = ['modernizr', 'tspan'];
    while (!mStyle.style) {
      afterInit = true;
      mStyle.modElem = createElement(elems.shift());
      mStyle.style = mStyle.modElem.style;
    }

    // Delete the objects if we created them.
    function cleanElems() {
      if (afterInit) {
        delete mStyle.style;
        delete mStyle.modElem;
      }
    }

    propsLength = props.length;
    for (i = 0; i < propsLength; i++) {
      prop = props[i];
      before = mStyle.style[prop];

      if (contains(prop, '-')) {
        prop = cssToDOM(prop);
      }

      if (mStyle.style[prop] !== undefined) {

        // If value to test has been passed in, do a set-and-check test.
        // 0 (integer) is a valid property value, so check that `value` isn't
        // undefined, rather than just checking it's truthy.
        if (!skipValueTest && !is(value, 'undefined')) {

          // Needs a try catch block because of old IE. This is slow, but will
          // be avoided in most cases because `skipValueTest` will be used.
          try {
            mStyle.style[prop] = value;
          } catch (e) {}

          // If the property value has changed, we assume the value used is
          // supported. If `value` is empty string, it'll fail here (because
          // it hasn't changed), which matches how browsers have implemented
          // CSS.supports()
          if (mStyle.style[prop] != before) {
            cleanElems();
            return prefixed == 'pfx' ? prop : true;
          }
        }
        // Otherwise just return true, or the property name if this is a
        // `prefixed()` call
        else {
          cleanElems();
          return prefixed == 'pfx' ? prop : true;
        }
      }
    }
    cleanElems();
    return false;
  }

  ;

  /**
   * testPropsAll tests a list of DOM properties we want to check against.
   * We specify literally ALL possible (known and/or likely) properties on
   * the element including the non-vendor prefixed one, for forward-
   * compatibility.
   *
   * @access private
   * @function testPropsAll
   * @param {string} prop - A string of the property to test for
   * @param {string|object} [prefixed] - An object to check the prefixed properties on. Use a string to skip
   * @param {HTMLElement|SVGElement} [elem] - An element used to test the property and value against
   * @param {string} [value] - A string of a css value
   * @param {boolean} [skipValueTest] - An boolean representing if you want to test if value sticks when set
   */
  function testPropsAll(prop, prefixed, elem, value, skipValueTest) {

    var ucProp = prop.charAt(0).toUpperCase() + prop.slice(1),
    props = (prop + ' ' + cssomPrefixes.join(ucProp + ' ') + ucProp).split(' ');

    // did they call .prefixed('boxSizing') or are we just testing a prop?
    if (is(prefixed, 'string') || is(prefixed, 'undefined')) {
      return testProps(props, prefixed, value, skipValueTest);

      // otherwise, they called .prefixed('requestAnimationFrame', window[, elem])
    } else {
      props = (prop + ' ' + (domPrefixes).join(ucProp + ' ') + ucProp).split(' ');
      return testDOMProps(props, prefixed, elem);
    }
  }

  // Modernizr.testAllProps() investigates whether a given style property,
  // or any of its vendor-prefixed variants, is recognized
  //
  // Note that the property names must be provided in the camelCase variant.
  // Modernizr.testAllProps('boxSizing')
  ModernizrProto.testAllProps = testPropsAll;

  

  /**
   * testAllProps determines whether a given CSS property is supported in the browser
   *
   * @memberof Modernizr
   * @name Modernizr.testAllProps
   * @optionName Modernizr.testAllProps()
   * @optionProp testAllProps
   * @access public
   * @function testAllProps
   * @param {string} prop - String naming the property to test (either camelCase or kebab-case)
   * @param {string} [value] - String of the value to test
   * @param {boolean} [skipValueTest=false] - Whether to skip testing that the value is supported when using non-native detection
   * @example
   *
   * testAllProps determines whether a given CSS property, in some prefixed form,
   * is supported by the browser.
   *
   * ```js
   * testAllProps('boxSizing')  // true
   * ```
   *
   * It can optionally be given a CSS value in string form to test if a property
   * value is valid
   *
   * ```js
   * testAllProps('display', 'block') // true
   * testAllProps('display', 'penguin') // false
   * ```
   *
   * A boolean can be passed as a third parameter to skip the value check when
   * native detection (@supports) isn't available.
   *
   * ```js
   * testAllProps('shapeOutside', 'content-box', true);
   * ```
   */

  function testAllProps(prop, value, skipValueTest) {
    return testPropsAll(prop, undefined, undefined, value, skipValueTest);
  }
  ModernizrProto.testAllProps = testAllProps;
  
/*!
{
  "name": "Flexbox",
  "property": "flexbox",
  "caniuse": "flexbox",
  "tags": ["css"],
  "notes": [{
    "name": "The _new_ flexbox",
    "href": "http://dev.w3.org/csswg/css3-flexbox"
  }],
  "warnings": [
    "A `true` result for this detect does not imply that the `flex-wrap` property is supported; see the `flexwrap` detect."
  ]
}
!*/
/* DOC
Detects support for the Flexible Box Layout model, a.k.a. Flexbox, which allows easy manipulation of layout order and sizing within a container.
*/

  Modernizr.addTest('flexbox', testAllProps('flexBasis', '1px', true));

/*!
{
  "name": "Flexbox (legacy)",
  "property": "flexboxlegacy",
  "tags": ["css"],
  "polyfills": ["flexie"],
  "notes": [{
    "name": "The _old_ flexbox",
    "href": "http://www.w3.org/TR/2009/WD-css3-flexbox-20090723/"
  }]
}
!*/

  Modernizr.addTest('flexboxlegacy', testAllProps('boxDirection', 'reverse', true));

/*!
{
  "name": "Flexbox (tweener)",
  "property": "flexboxtweener",
  "tags": ["css"],
  "polyfills": ["flexie"],
  "notes": [{
    "name": "The _inbetween_ flexbox",
    "href": "http://www.w3.org/TR/2011/WD-css3-flexbox-20111129/"
  }],
  "warnings": ["This represents an old syntax, not the latest standard syntax."]
}
!*/

  Modernizr.addTest('flexboxtweener', testAllProps('flexAlign', 'end', true));

/*!
{
  "name": "Flex Line Wrapping",
  "property": "flexwrap",
  "tags": ["css", "flexbox"],
  "notes": [{
    "name": "W3C Flexible Box Layout spec",
    "href": "http://dev.w3.org/csswg/css3-flexbox"
  }],
  "warnings": [
    "Does not imply a modern implementation – see documentation."
  ]
}
!*/
/* DOC
Detects support for the `flex-wrap` CSS property, part of Flexbox, which isn’t present in all Flexbox implementations (notably Firefox).

This featured in both the 'tweener' syntax (implemented by IE10) and the 'modern' syntax (implemented by others). This detect will return `true` for either of these implementations, as long as the `flex-wrap` property is supported. So to ensure the modern syntax is supported, use together with `Modernizr.flexbox`:

```javascript
if (Modernizr.flexbox && Modernizr.flexwrap) {
  // Modern Flexbox with `flex-wrap` supported
}
else {
  // Either old Flexbox syntax, or `flex-wrap` not supported
}
```
*/

  Modernizr.addTest('flexwrap', testAllProps('flexWrap', 'wrap', true));


  // Run each test
  testRunner();

  // Remove the "no-js" class if it exists
  setClasses(classes);

  delete ModernizrProto.addTest;
  delete ModernizrProto.addAsyncTest;

  // Run the things that are supposed to run after the tests
  for (var i = 0; i < Modernizr._q.length; i++) {
    Modernizr._q[i]();
  }

  // Leak Modernizr namespace
  window.Modernizr = Modernizr;


;

})(window, document);
/**
 * @license
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * A component handler interface using the revealing module design pattern.
 * More details on this design pattern here:
 * https://github.com/jasonmayes/mdl-component-design-pattern
 *
 * @author Jason Mayes.
 */
/* exported componentHandler */

// Pre-defining the componentHandler interface, for closure documentation and
// static verification.
var componentHandler = {
  /**
   * Searches existing DOM for elements of our component type and upgrades them
   * if they have not already been upgraded.
   *
   * @param {string=} optJsClass the programatic name of the element class we
   * need to create a new instance of.
   * @param {string=} optCssClass the name of the CSS class elements of this
   * type will have.
   */
  upgradeDom: function(optJsClass, optCssClass) {},
  /**
   * Upgrades a specific element rather than all in the DOM.
   *
   * @param {!Element} element The element we wish to upgrade.
   * @param {string=} optJsClass Optional name of the class we want to upgrade
   * the element to.
   */
  upgradeElement: function(element, optJsClass) {},
  /**
   * Upgrades a specific list of elements rather than all in the DOM.
   *
   * @param {!Element|!Array<!Element>|!NodeList|!HTMLCollection} elements
   * The elements we wish to upgrade.
   */
  upgradeElements: function(elements) {},
  /**
   * Upgrades all registered components found in the current DOM. This is
   * automatically called on window load.
   */
  upgradeAllRegistered: function() {},
  /**
   * Allows user to be alerted to any upgrades that are performed for a given
   * component type
   *
   * @param {string} jsClass The class name of the MDL component we wish
   * to hook into for any upgrades performed.
   * @param {function(!HTMLElement)} callback The function to call upon an
   * upgrade. This function should expect 1 parameter - the HTMLElement which
   * got upgraded.
   */
  registerUpgradedCallback: function(jsClass, callback) {},
  /**
   * Registers a class for future use and attempts to upgrade existing DOM.
   *
   * @param {componentHandler.ComponentConfigPublic} config the registration configuration
   */
  register: function(config) {},
  /**
   * Downgrade either a given node, an array of nodes, or a NodeList.
   *
   * @param {!Node|!Array<!Node>|!NodeList} nodes
   */
  downgradeElements: function(nodes) {}
};

componentHandler = (function() {
  'use strict';

  /** @type {!Array<componentHandler.ComponentConfig>} */
  var registeredComponents_ = [];

  /** @type {!Array<componentHandler.Component>} */
  var createdComponents_ = [];

  var downgradeMethod_ = 'mdlDowngrade';
  var componentConfigProperty_ = 'mdlComponentConfigInternal_';

  /**
   * Searches registered components for a class we are interested in using.
   * Optionally replaces a match with passed object if specified.
   *
   * @param {string} name The name of a class we want to use.
   * @param {componentHandler.ComponentConfig=} optReplace Optional object to replace match with.
   * @return {!Object|boolean}
   * @private
   */
  function findRegisteredClass_(name, optReplace) {
    for (var i = 0; i < registeredComponents_.length; i++) {
      if (registeredComponents_[i].className === name) {
        if (typeof optReplace !== 'undefined') {
          registeredComponents_[i] = optReplace;
        }
        return registeredComponents_[i];
      }
    }
    return false;
  }

  /**
   * Returns an array of the classNames of the upgraded classes on the element.
   *
   * @param {!Element} element The element to fetch data from.
   * @return {!Array<string>}
   * @private
   */
  function getUpgradedListOfElement_(element) {
    var dataUpgraded = element.getAttribute('data-upgraded');
    // Use `['']` as default value to conform the `,name,name...` style.
    return dataUpgraded === null ? [''] : dataUpgraded.split(',');
  }

  /**
   * Returns true if the given element has already been upgraded for the given
   * class.
   *
   * @param {!Element} element The element we want to check.
   * @param {string} jsClass The class to check for.
   * @returns {boolean}
   * @private
   */
  function isElementUpgraded_(element, jsClass) {
    var upgradedList = getUpgradedListOfElement_(element);
    return upgradedList.indexOf(jsClass) !== -1;
  }

  /**
   * Searches existing DOM for elements of our component type and upgrades them
   * if they have not already been upgraded.
   *
   * @param {string=} optJsClass the programatic name of the element class we
   * need to create a new instance of.
   * @param {string=} optCssClass the name of the CSS class elements of this
   * type will have.
   */
  function upgradeDomInternal(optJsClass, optCssClass) {
    if (typeof optJsClass === 'undefined' &&
        typeof optCssClass === 'undefined') {
      for (var i = 0; i < registeredComponents_.length; i++) {
        upgradeDomInternal(registeredComponents_[i].className,
            registeredComponents_[i].cssClass);
      }
    } else {
      var jsClass = /** @type {string} */ (optJsClass);
      if (typeof optCssClass === 'undefined') {
        var registeredClass = findRegisteredClass_(jsClass);
        if (registeredClass) {
          optCssClass = registeredClass.cssClass;
        }
      }

      var elements = document.querySelectorAll('.' + optCssClass);
      for (var n = 0; n < elements.length; n++) {
        upgradeElementInternal(elements[n], jsClass);
      }
    }
  }

  /**
   * Upgrades a specific element rather than all in the DOM.
   *
   * @param {!Element} element The element we wish to upgrade.
   * @param {string=} optJsClass Optional name of the class we want to upgrade
   * the element to.
   */
  function upgradeElementInternal(element, optJsClass) {
    // Verify argument type.
    if (!(typeof element === 'object' && element instanceof Element)) {
      throw new Error('Invalid argument provided to upgrade MDL element.');
    }
    var upgradedList = getUpgradedListOfElement_(element);
    var classesToUpgrade = [];
    // If jsClass is not provided scan the registered components to find the
    // ones matching the element's CSS classList.
    if (!optJsClass) {
      var classList = element.classList;
      registeredComponents_.forEach(function(component) {
        // Match CSS & Not to be upgraded & Not upgraded.
        if (classList.contains(component.cssClass) &&
            classesToUpgrade.indexOf(component) === -1 &&
            !isElementUpgraded_(element, component.className)) {
          classesToUpgrade.push(component);
        }
      });
    } else if (!isElementUpgraded_(element, optJsClass)) {
      classesToUpgrade.push(findRegisteredClass_(optJsClass));
    }

    // Upgrade the element for each classes.
    for (var i = 0, n = classesToUpgrade.length, registeredClass; i < n; i++) {
      registeredClass = classesToUpgrade[i];
      if (registeredClass) {
        // Mark element as upgraded.
        upgradedList.push(registeredClass.className);
        element.setAttribute('data-upgraded', upgradedList.join(','));
        var instance = new registeredClass.classConstructor(element);
        instance[componentConfigProperty_] = registeredClass;
        createdComponents_.push(instance);
        // Call any callbacks the user has registered with this component type.
        for (var j = 0, m = registeredClass.callbacks.length; j < m; j++) {
          registeredClass.callbacks[j](element);
        }

        if (registeredClass.widget) {
          // Assign per element instance for control over API
          element[registeredClass.className] = instance;
        }
      } else {
        throw new Error(
          'Unable to find a registered component for the given class.');
      }

      var ev = document.createEvent('Events');
      ev.initEvent('mdl-componentupgraded', true, true);
      element.dispatchEvent(ev);
    }
  }

  /**
   * Upgrades a specific list of elements rather than all in the DOM.
   *
   * @param {!Element|!Array<!Element>|!NodeList|!HTMLCollection} elements
   * The elements we wish to upgrade.
   */
  function upgradeElementsInternal(elements) {
    if (!Array.isArray(elements)) {
      if (typeof elements.item === 'function') {
        elements = Array.prototype.slice.call(/** @type {Array} */ (elements));
      } else {
        elements = [elements];
      }
    }
    for (var i = 0, n = elements.length, element; i < n; i++) {
      element = elements[i];
      if (element instanceof HTMLElement) {
        upgradeElementInternal(element);
        if (element.children.length > 0) {
          upgradeElementsInternal(element.children);
        }
      }
    }
  }

  /**
   * Registers a class for future use and attempts to upgrade existing DOM.
   *
   * @param {componentHandler.ComponentConfigPublic} config
   */
  function registerInternal(config) {
    // In order to support both Closure-compiled and uncompiled code accessing
    // this method, we need to allow for both the dot and array syntax for
    // property access. You'll therefore see the `foo.bar || foo['bar']`
    // pattern repeated across this method.
    var widgetMissing = (typeof config.widget === 'undefined' &&
        typeof config['widget'] === 'undefined');
    var widget = true;

    if (!widgetMissing) {
      widget = config.widget || config['widget'];
    }

    var newConfig = /** @type {componentHandler.ComponentConfig} */ ({
      classConstructor: config.constructor || config['constructor'],
      className: config.classAsString || config['classAsString'],
      cssClass: config.cssClass || config['cssClass'],
      widget: widget,
      callbacks: []
    });

    registeredComponents_.forEach(function(item) {
      if (item.cssClass === newConfig.cssClass) {
        throw new Error('The provided cssClass has already been registered: ' + item.cssClass);
      }
      if (item.className === newConfig.className) {
        throw new Error('The provided className has already been registered');
      }
    });

    if (config.constructor.prototype
        .hasOwnProperty(componentConfigProperty_)) {
      throw new Error(
          'MDL component classes must not have ' + componentConfigProperty_ +
          ' defined as a property.');
    }

    var found = findRegisteredClass_(config.classAsString, newConfig);

    if (!found) {
      registeredComponents_.push(newConfig);
    }
  }

  /**
   * Allows user to be alerted to any upgrades that are performed for a given
   * component type
   *
   * @param {string} jsClass The class name of the MDL component we wish
   * to hook into for any upgrades performed.
   * @param {function(!HTMLElement)} callback The function to call upon an
   * upgrade. This function should expect 1 parameter - the HTMLElement which
   * got upgraded.
   */
  function registerUpgradedCallbackInternal(jsClass, callback) {
    var regClass = findRegisteredClass_(jsClass);
    if (regClass) {
      regClass.callbacks.push(callback);
    }
  }

  /**
   * Upgrades all registered components found in the current DOM. This is
   * automatically called on window load.
   */
  function upgradeAllRegisteredInternal() {
    for (var n = 0; n < registeredComponents_.length; n++) {
      upgradeDomInternal(registeredComponents_[n].className);
    }
  }

  /**
   * Finds a created component by a given DOM node.
   *
   * @param {!Node} node
   * @return {?componentHandler.Component}
   */
  function findCreatedComponentByNodeInternal(node) {
    for (var n = 0; n < createdComponents_.length; n++) {
      var component = createdComponents_[n];
      if (component.element_ === node) {
        return component;
      }
    }
    return null;
  }

  /**
   * Check the component for the downgrade method.
   * Execute if found.
   * Remove component from createdComponents list.
   *
   * @param {?componentHandler.Component} component
   */
  function deconstructComponentInternal(component) {
    if (component &&
        component[componentConfigProperty_]
          .classConstructor.prototype
          .hasOwnProperty(downgradeMethod_)) {
      component[downgradeMethod_]();
      var componentIndex = createdComponents_.indexOf(component);
      createdComponents_.splice(componentIndex, 1);

      var upgrades = component.element_.getAttribute('data-upgraded').split(',');
      var componentPlace = upgrades.indexOf(
          component[componentConfigProperty_].classAsString);
      upgrades.splice(componentPlace, 1);
      component.element_.setAttribute('data-upgraded', upgrades.join(','));

      var ev = document.createEvent('Events');
      ev.initEvent('mdl-componentdowngraded', true, true);
      component.element_.dispatchEvent(ev);
    }
  }

  /**
   * Downgrade either a given node, an array of nodes, or a NodeList.
   *
   * @param {!Node|!Array<!Node>|!NodeList} nodes
   */
  function downgradeNodesInternal(nodes) {
    /**
     * Auxiliary function to downgrade a single node.
     * @param  {!Node} node the node to be downgraded
     */
    var downgradeNode = function(node) {
      deconstructComponentInternal(findCreatedComponentByNodeInternal(node));
    };
    if (nodes instanceof Array || nodes instanceof NodeList) {
      for (var n = 0; n < nodes.length; n++) {
        downgradeNode(nodes[n]);
      }
    } else if (nodes instanceof Node) {
      downgradeNode(nodes);
    } else {
      throw new Error('Invalid argument provided to downgrade MDL nodes.');
    }
  }

  // Now return the functions that should be made public with their publicly
  // facing names...
  return {
    upgradeDom: upgradeDomInternal,
    upgradeElement: upgradeElementInternal,
    upgradeElements: upgradeElementsInternal,
    upgradeAllRegistered: upgradeAllRegisteredInternal,
    registerUpgradedCallback: registerUpgradedCallbackInternal,
    register: registerInternal,
    downgradeElements: downgradeNodesInternal
  };
})();

/**
 * Describes the type of a registered component type managed by
 * componentHandler. Provided for benefit of the Closure compiler.
 *
 * @typedef {{
 *   constructor: Function,
 *   classAsString: string,
 *   cssClass: string,
 *   widget: (string|boolean|undefined)
 * }}
 */
componentHandler.ComponentConfigPublic;  // jshint ignore:line

/**
 * Describes the type of a registered component type managed by
 * componentHandler. Provided for benefit of the Closure compiler.
 *
 * @typedef {{
 *   constructor: !Function,
 *   className: string,
 *   cssClass: string,
 *   widget: (string|boolean),
 *   callbacks: !Array<function(!HTMLElement)>
 * }}
 */
componentHandler.ComponentConfig;  // jshint ignore:line

/**
 * Created component (i.e., upgraded element) type as managed by
 * componentHandler. Provided for benefit of the Closure compiler.
 *
 * @typedef {{
 *   element_: !HTMLElement,
 *   className: string,
 *   classAsString: string,
 *   cssClass: string,
 *   widget: string
 * }}
 */
componentHandler.Component;  // jshint ignore:line

// Export all symbols, for the benefit of Closure compiler.
// No effect on uncompiled code.
componentHandler['upgradeDom'] = componentHandler.upgradeDom;
componentHandler['upgradeElement'] = componentHandler.upgradeElement;
componentHandler['upgradeElements'] = componentHandler.upgradeElements;
componentHandler['upgradeAllRegistered'] =
    componentHandler.upgradeAllRegistered;
componentHandler['registerUpgradedCallback'] =
    componentHandler.registerUpgradedCallback;
componentHandler['register'] = componentHandler.register;
componentHandler['downgradeElements'] = componentHandler.downgradeElements;
window.componentHandler = componentHandler;
window['componentHandler'] = componentHandler;

window.addEventListener('load', function() {
  'use strict';

  /**
   * Performs a "Cutting the mustard" test. If the browser supports the features
   * tested, adds a mdl-js class to the <html> element. It then upgrades all MDL
   * components requiring JavaScript.
   */
  if ('classList' in document.createElement('div') &&
      'querySelector' in document &&
      'addEventListener' in window && Array.prototype.forEach) {
    document.documentElement.classList.add('mdl-js');
    componentHandler.upgradeAllRegistered();
  } else {
    /**
     * Dummy function to avoid JS errors.
     */
    componentHandler.upgradeElement = function() {};
    /**
     * Dummy function to avoid JS errors.
     */
    componentHandler.register = function() {};
  }
});

!function(a,b){"function"==typeof define&&define.amd?define([],function(){return a.svg4everybody=b()}):"object"==typeof exports?module.exports=b():a.svg4everybody=b()}(this,function(){/*! svg4everybody v2.0.0 | github.com/jonathantneal/svg4everybody */
function a(a,b){if(b){var c=!a.getAttribute("viewBox")&&b.getAttribute("viewBox"),d=document.createDocumentFragment(),e=b.cloneNode(!0);for(c&&a.setAttribute("viewBox",c);e.childNodes.length;)d.appendChild(e.firstChild);a.appendChild(d)}}function b(b){b.onreadystatechange=function(){if(4===b.readyState){var c=document.createElement("x");c.innerHTML=b.responseText,b.s.splice(0).map(function(b){a(b[0],c.querySelector("#"+b[1].replace(/(\W)/g,"\\$1")))})}},b.onreadystatechange()}function c(c){function d(){for(var c;c=e[0];){var j=c.parentNode;if(j&&/svg/i.test(j.nodeName)){var k=c.getAttribute("xlink:href");if(f&&(!g||g(k,j,c))){var l=k.split("#"),m=l[0],n=l[1];if(j.removeChild(c),m.length){var o=i[m]=i[m]||new XMLHttpRequest;o.s||(o.s=[],o.open("GET",m),o.send()),o.s.push([j,n]),b(o)}else a(j,document.getElementById(n))}}}h(d,17)}c=c||{};var e=document.getElementsByTagName("use"),f="shim"in c?c.shim:/\bEdge\/12\b|\bTrident\/[567]\b|\bVersion\/7.0 Safari\b/.test(navigator.userAgent)||(navigator.userAgent.match(/AppleWebKit\/(\d+)/)||[])[1]<537,g=c.validate,h=window.requestAnimationFrame||setTimeout,i={};f&&d()}return c});
'use strict';

(function () {
    'use strict';

    var WG = {
        _plugins: {},

        /**
         * Stores generated unique ids for plugin instances
         */
        _uuids: [],

        Error: {
            ALREADY_ADDED: 'The provided Component has already been added'
        },

        addComponent: function addComponent(component) {
            var name = this.getComponentName(component);

            if (this._plugins[name.toLowerCase()]) {
                // throw new Error(WG.Error.ALREADY_ADDED);
                return;
            }

            this._plugins[name.toLowerCase()] = this[name] = component;
        },

        registerComponent: function registerComponent(component) {
            var componentName = this.convertComponentName(this.getComponentName(component));

            component.uuid = this.generateId(6, componentName);

            this._uuids.push(component.uuid);

            if (!component.$element.attr('data-uuid-' + componentName)) {
                component.$element.attr('data-uuid-' + componentName, component.uuid);
            }

            component.$element.trigger('init.wg.' + componentName);
        },

        unregisterComponent: function unregisterComponent(component) {
            var componentName = this.convertComponentName(this.getComponentName(component));

            this._uuids.splice(this._uuids.indexOf(component.uuid), 1);

            component.$element.removeAttr('data-uuid-' + componentName).removeAttr('wg-instance').trigger('destroyed.wg.' + componentName);

            for (var prop in component) {
                component[prop] = null;
            }
        },

        initAllRegistered: function initAllRegistered() {
            var plugins = Object.keys(this._plugins),
                self = this;

            function parseValue(str) {
                if (/true/.test(str)) {
                    return true;
                } else if (/false/.test(str)) {
                    return false;
                } else if (!isNaN(str * 1)) {
                    return parseFloat(str);
                }
                return str;
            }

            $.each(plugins, function (i, name) {
                var cname = name.toLowerCase(),
                    Plugin = self._plugins[cname],
                    $elem = $('[data-wg-' + cname + ']'),
                    pname = self.getComponentName(Plugin).toLowerCase();

                $elem.each(function () {
                    var $el = $(this),
                        opts = {},
                        attrs = $el.attr('data-wg-' + pname);

                    if ($el.data('wg-instance')) {
                        return;
                    }

                    if (attrs) {
                        attrs.split(';').forEach(function (e) {
                            var opt = e.split(':').map(function (el) {
                                return el.trim();
                            });

                            if (opt[0]) {
                                opts[opt[0]] = parseValue(opt[1]);
                            }
                        });
                    }

                    $el.data('wg-instance', new Plugin($(this), opts));
                    $el.trigger('ready.wg.' + pname);
                });
            });
        },

        /**
         * Convert camelCase to kebab-case
         * Example: TogglerPlugin -> toggler-plugin
         */
        convertComponentName: function convertComponentName(name) {
            return name.replace(/([a-z])([A-Z])/g, '$1-$2').toLowerCase();
        },

        getComponentName: function getComponentName(component) {
            if (Function.prototype.name === undefined) {
                var funcNameRegex = /function\s([^(]{1,})\(/;
                var results = funcNameRegex.exec(component.toString());
                return results && results.length > 1 ? results[1].trim() : '';
            } else if (component.prototype === undefined) {
                return component.constructor.name;
            } else {
                return component.prototype.constructor.name;
            }
        },

        generateId: function generateId(ns) {
            return Math.round(Math.pow(36, 7) - Math.random() * Math.pow(36, 6)).toString(36).slice(1) + (ns ? '-' + ns : '');
        }
    };

    $.fn.wgInstance = function () {
        return this.data('wg-instance');
    };

    WG.utils = {
        isIOS: function isIOS() {
            return (/iP(ad|hone|od).*OS/.test(window.navigator.userAgent)
            );
        },
        getCookie: function getCookie(name) {
            var matches = document.cookie.match(new RegExp('(?:^|; )' + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + '=([^;]*)'));
            return matches ? decodeURIComponent(matches[1]) : undefined;
        },
        setCookie: function setCookie(name, value, options) {
            options = options || {};

            var expires = options.expires;

            if (typeof expires == 'number' && expires) {
                var d = new Date();
                d.setTime(d.getTime() + expires * 1000);
                expires = options.expires = d;
            }
            if (expires && expires.toUTCString) {
                options.expires = expires.toUTCString();
            }

            value = encodeURIComponent(value);

            var updatedCookie = name + '=' + value;

            for (var propName in options) {
                updatedCookie += '; ' + propName;
                var propValue = options[propName];
                if (propValue !== true) {
                    updatedCookie += '=' + propValue;
                }
            }

            document.cookie = updatedCookie;
        },
        deleteCookie: function deleteCookie(name) {
            WG.utils.setCookie(name, '', {
                expires: -1
            });
        },
        utf8_to_b64: function utf8_to_b64(str) {
            return window.btoa(encodeURIComponent(str));
        },
        b64_to_utf8: function b64_to_utf8(str) {
            return decodeURIComponent(window.atob(str));
        }
    };

    window.WG = WG;

    window.addEventListener('load', function () {
        WG.initAllRegistered();
    });
})();
'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

(function () {
    'use strict';

    var Button = function () {
        function Button(element) {
            var options = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

            _classCallCheck(this, Button);

            this.$element = element;

            this.options = $.extend({}, Button.defaults, options);

            this._init();

            WG.registerComponent(this);
        }

        _createClass(Button, [{
            key: '_init',
            value: function _init() {
                this._events();
            }
        }, {
            key: '_events',
            value: function _events() {}
        }, {
            key: 'toggle',
            value: function toggle() {
                this.$element.toggleClass(Button.classes.active);

                if (this.isOn()) {
                    this.$element.trigger('on.wg.button');
                } else {
                    this.$element.trigger('off.wg.button');
                }
            }
        }, {
            key: 'isOn',
            value: function isOn() {
                return this.$element.hasClass(Button.classes.active);
            }
        }, {
            key: 'loading',
            value: function loading() {
                var _this = this;

                var time = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : 3000;
                var resetOnEnd = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;

                if (this.$element.hasClass(Button.classes.loading)) {
                    return;
                }

                if (!this._progressElem) {
                    this._renderProgressElem();
                }

                this.reset();
                // this.disable();

                this.$element.addClass(Button.classes.loading);

                this._progressElem.css({
                    display: 'block',
                    transition: 'width ' + time / 1000 + 's'
                });

                this._progressElem.width('100%');

                setTimeout(function () {
                    if (resetOnEnd) {
                        _this.reset();
                    }
                }, time);
            }
        }, {
            key: '_renderProgressElem',
            value: function _renderProgressElem() {
                this._progressElem = $('<span class="' + Button.classes.progressElem + '">');
                this._progressElem.hide();

                this.$element.append(this._progressElem);
            }
        }, {
            key: 'disable',
            value: function disable() {
                this.$element.attr('disabled', 'disabled');
                this.$element.addClass(Button.classes.disabled);
            }
        }, {
            key: 'enable',
            value: function enable() {
                this.$element.removeAttr('disabled');
                this.$element.removeClass(Button.classes.disabled);
            }
        }, {
            key: 'reset',
            value: function reset() {
                var _this2 = this;

                this.enable();

                if (!this._progressElem) {
                    return;
                }

                this._progressElem.fadeOut(150, function () {
                    _this2._progressElem.width(0);
                    _this2.$element.removeClass(Button.classes.loading);
                });
            }
        }, {
            key: 'destroy',
            value: function destroy() {
                this.$element.off('.wg.button');
                WG.unregisterComponent(this);
            }
        }]);

        return Button;
    }();

    Button.classes = {
        active: 'wg-btn--active',
        disabled: 'wg-btn--disabled',
        loading: 'wg-btn--loading',
        progressElem: 'wg-btn-progress'
    };

    Button.defaults = {};

    WG.addComponent(Button);
})();
'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

/**
 * Modal window plugin.
 *
 * Events:
 * - init.wg.modal - This event fires when the modal is registered in WG.
 * - show.wg.modal - This event fires immediately when the show instance method is called.
 * - shown.wg.modal - This event is fired when the modal has been made visible to the user (will wait for 300ms timer emulating CSS transitions to complete).
 * - hide.wg.modal - This event is fired immediately when the hide instance method has been called.
 * - hidden.wg.modal - This event is fired when the modal has finished being hidden from the user (will wait for 300ms timer emulating CSS transitions to complete).
 *
 * Options:
 * - hideOnClick - Allows a click on the body/overlay to hide the modal.
 * - hideOnEsc - Allows the modal to hide if the user presses the ESC key.
 * - resetOnHide - Resets the modal content. This prevents a running video to keep going in the background.
 * - animationIn - Motion-UI class to use for animated elements. If none used, defaults to simple show/hide.
 *
 */

(function () {
    'use strict';

    var Modal = function () {
        function Modal(element) {
            var options = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

            _classCallCheck(this, Modal);

            this.$element = element;
            this.options = $.extend({}, Modal.defaults, options);

            this.$body = $(document.body);
            this.$document = $(document);

            this._init();

            WG.registerComponent(this);
        }

        _createClass(Modal, [{
            key: '_init',
            value: function _init() {
                this.id = this.$element.attr('id');
                this.isActive = false;
                this.$overlay = this._makeOverlay();
                this.$element.detach().appendTo(this.$overlay);
                this.isIOS = WG.utils.isIOS();

                this.$anchor = $('[data-show="' + this.id + '"]');
                if (this.$anchor.length) {
                    var anchorId = this.$anchor[0].id || WG.generateId(6, 'wg-modal');
                    this.$anchor.attr({
                        'aria-controls': this.id,
                        id: anchorId,
                        'aria-haspopup': true,
                        tabindex: 0
                    });
                }

                this._events();
            }
        }, {
            key: '_makeOverlay',
            value: function _makeOverlay() {
                var $overlay = $('<div></div>').addClass(Modal.classes.overlay).attr({ tabindex: -1, 'aria-hidden': true }).appendTo('body');

                if (this.options.animationIn) {
                    $overlay.addClass(Modal.classes.fade);
                }

                return $overlay;
            }
        }, {
            key: '_events',
            value: function _events() {
                var _this = this;

                if (this.options.hideOnClick) {
                    this.$overlay.off('.wg.modal').on('click.wg.modal', function (e) {
                        if (e.target === _this.$element[0] || $.contains(_this.$element[0], e.target)) {
                            return;
                        }

                        _this.hide();
                    });
                }

                if (this.options.hideOnEsc) {
                    this.$document.on('keydown.wg.modal:' + this.id, function (e) {
                        if (e.which === 27) {
                            e.stopPropagation();
                            e.preventDefault();
                            _this.hide();
                        }
                    });
                }

                if (this.$anchor.length) {
                    this.$anchor.on('keydown.wg.modal', function (e) {
                        if (e.which === 13 || e.which === 32) {
                            e.stopPropagation();
                            e.preventDefault();
                            _this.show();
                        }
                    });

                    this.$anchor.on('click.wg.modal', function () {
                        _this.show();
                    });
                }
            }
        }, {
            key: 'show',
            value: function show() {
                var _this2 = this;

                this.$element.trigger('show.wg.modal');

                if (this.isActive) {
                    return;
                }

                this.isActive = true;

                if (this.isIOS) {
                    $('html, body').addClass(Modal.classes.isModalOpen).scrollTop(window.pageYOffset);
                } else {
                    this.$body.addClass(Modal.classes.isModalOpen);
                }

                this.$overlay.show(0);
                if (this.options.animationIn) {
                    this.$overlay.addClass('in');
                    setTimeout(function () {
                        _this2.$element.trigger('shown.wg.modal');
                    }, 300);
                } else {
                    this.$element.trigger('shown.wg.modal');
                }

                this.$element.show().scrollTop(0);

                this.$element.on('click', '[data-wg-hide]', this.hide.bind(this));
            }
        }, {
            key: 'hide',
            value: function hide() {
                var _this3 = this;

                this.$element.trigger('hide.wg.modal');

                if (!this.isActive) {
                    return;
                }

                this.$element.hide().off('click', '[data-wg-hide]');

                this.$element.off('click', '[data-wg-hide]', this.hide.bind(this));

                if (this.options.animationIn) {
                    this.$overlay.removeClass('in');

                    setTimeout(function () {
                        _this3._hideModal();
                    }, 300);
                } else {
                    this._hideModal();
                }

                if (this.options.resetOnHide) {
                    this.$element.html(this.$element.html());
                }

                this.isActive = false;
            }
        }, {
            key: '_hideModal',
            value: function _hideModal() {
                this.$overlay.hide(0);

                if (this.isIOS) {
                    $('html, body').removeClass(Modal.classes.isModalOpen);
                } else {
                    this.$body.removeClass(Modal.classes.isModalOpen);
                }

                this.$element.trigger('hidden.wg.modal');
            }
        }, {
            key: 'toggle',
            value: function toggle() {
                return this.isActive ? this.hide() : this.show();
            }
        }, {
            key: 'destroy',
            value: function destroy() {
                this.$element.appendTo($('body'));
                this.$overlay.hide().off().remove();
                this.$element.hide().off();
                this.$anchor.off('.wg');
                this.$document.off('.modal:' + this.id);

                WG.unregisterComponent(this);
            }
        }]);

        return Modal;
    }();

    Modal.defaults = {
        hideOnClick: true,
        hideOnEsc: true,
        resetOnHide: false,
        animationIn: true
    };

    Modal.classes = {
        overlay: 'wg-modal-overlay',
        show: 'wg-modal--show',
        fade: 'wg-modal--fade',
        isModalOpen: 'is-modal-open'
    };

    WG.addComponent(Modal);
})();
'use strict';

(function () {
    'use strict';

    window.addEventListener('load', function () {
        window.svg4everybody();
    });
})();
'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

(function () {
    'use strict';

    var Toggler = function () {
        function Toggler(element) {
            var options = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

            _classCallCheck(this, Toggler);

            this.$element = element;

            this.options = $.extend({}, Toggler.defaults, options);
            this.$target = $(options.target);

            this._init();

            WG.registerComponent(this);
        }

        _createClass(Toggler, [{
            key: '_init',
            value: function _init() {
                this._updateARIA();
                this._events();
            }
        }, {
            key: '_events',
            value: function _events() {
                var _this = this;

                this.$element.on('click.wg', function () {
                    _this.toggle();
                });
            }
        }, {
            key: 'toggle',
            value: function toggle() {
                this._toggleClass();
            }
        }, {
            key: '_toggleClass',
            value: function _toggleClass() {
                this.$target.toggleClass(this.options.cls);

                if (this.isOn()) {
                    this.$element.trigger('on.wg.toggler');
                } else {
                    this.$element.trigger('off.wg.toggler');
                }

                this._updateARIA();
            }
        }, {
            key: 'isOn',
            value: function isOn() {
                return this.$target.hasClass(this.options.cls);
            }
        }, {
            key: '_updateARIA',
            value: function _updateARIA() {
                this.$element.attr('aria-expanded', this.isOn() ? true : false);
            }
        }, {
            key: 'destroy',
            value: function destroy() {
                this.$element.off('.toggler');
                WG.unregisterComponent(this);
            }
        }]);

        return Toggler;
    }();

    Toggler.defaults = {};

    WG.addComponent(Toggler);
})();
'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

/**
 * Validation plugin.
 *
 * Events:
 * - valid.wg.validate - Fires when the input is done checking for validation. And the input is valid.
 * - invalid.wg.validate - Fires when the input is done checking for validation. And the input is invalid.
 * - formvalid.wg.validate - Fires when the form is finished validating. And the form is valid.
 * - forminvalid.wg.validate - Fires when the form is finished validating. And the form is invalid.
 *
 * Options:
 * - validateOnChange - Set to true to validate text inputs on value is changed.
 */

(function () {
    'use strict';

    var Validate = function () {
        function Validate(element) {
            var options = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

            _classCallCheck(this, Validate);

            this.$element = element;

            this.options = $.extend({}, Validate.defaults, options);

            this._init();

            WG.registerComponent(this);
        }

        _createClass(Validate, [{
            key: '_init',
            value: function _init() {
                this.$inputs = this.$element.find('input, textarea, select').not('[data-wg-ignore]');

                this._events();
            }
        }, {
            key: '_events',
            value: function _events() {
                var _this = this;

                this.$element.on('reset.wg.validate', function () {
                    _this.resetForm();
                }).on('submit.wg.validate', function () {
                    _this.validateForm();
                    return false;
                });

                if (this.options.validateOnChange) {
                    this.$inputs.on('change.wg.validate', function (e) {
                        _this.validateInput(e.target);
                    });
                }
            }
        }, {
            key: 'validateInput',
            value: function validateInput(elem) {
                var isValid = false,
                    $elem = $(elem);

                switch (elem.type) {
                    default:
                        isValid = this.validateTextInput(elem);
                }

                this[isValid ? 'hideError' : 'showError']($elem);

                $elem.trigger((isValid ? 'valid' : 'invalid') + '.wg.validate', [$elem]);

                return isValid;
            }
        }, {
            key: 'showError',
            value: function showError($elem) {
                $elem.addClass(Validate.classes.inputError);

                var $error = this.findFieldError($elem);

                if ($error.length) {
                    $error.addClass(Validate.classes.fieldErrorClass);
                }
            }
        }, {
            key: 'hideError',
            value: function hideError($elem) {
                $elem.removeClass(Validate.classes.inputError);

                var $error = this.findFieldError($elem);

                if ($error.length) {
                    $error.removeClass(Validate.classes.fieldErrorClass);
                }
            }
        }, {
            key: 'findFieldError',
            value: function findFieldError($elem) {
                var $error = $elem.siblings(Validate.classes.fieldErrorSelector);

                if (!$error.length) {
                    $error = $elem.parent().find(Validate.classes.fieldErrorSelector);
                }

                return $error;
            }
        }, {
            key: 'resetForm',
            value: function resetForm() {
                this.$element.find('.' + Validate.classes.inputError).removeClass(Validate.classes.inputError);

                this.$element.trigger('formreset.wg.validate');
            }

            /**
             * Validation order is next:
             * 1. pattern="email-business" - validate pattern is predefined and is a function
             * 2. pattern="email" - validate pattern is predefined
             * 3. pattern="%%regexp" - validate pattern is a regexp
             */

        }, {
            key: 'validateTextInput',
            value: function validateTextInput(input) {
                var $input = $(input),
                    value = $input.val(),
                    validateStates = [],
                    isRequired = $input.attr('required'),
                    patterns = ($input.attr('pattern') || $input.attr('type')).split(';');

                if (value.length) {
                    patterns.forEach(function (pattern) {
                        var isValid = false;

                        if (Validate.patterns.hasOwnProperty(pattern)) {
                            var check = Validate.patterns[pattern];

                            if (typeof check === 'function') {
                                isValid = check.call(this, value);
                            } else {
                                isValid = check.test(value);
                            }
                        } else if (pattern !== $input.attr('type')) {
                            isValid = new RegExp(pattern).test(value);
                        } else {
                            isValid = true;
                        }

                        validateStates.push(isValid);
                    });
                } else if (!isRequired) {
                    validateStates.push(true);
                }

                if (validateStates.length && validateStates.indexOf(false) === -1) {
                    return true;
                } else {
                    return false;
                }
            }
        }, {
            key: 'validateForm',
            value: function validateForm() {
                var result = [],
                    self = this;

                this.$inputs.each(function () {
                    result.push(self.validateInput(this));
                });

                var isValid = result.indexOf(false) === -1;

                this.$element.trigger((isValid ? 'formvalid' : 'forminvalid') + '.wg.validate', [this.$element]);

                return isValid;
            }
        }, {
            key: 'destroy',
            value: function destroy() {
                this.$element.off('.validate').find('[data-wg-error]').css('display', 'none').find('.' + Validate.classes.inputError).removeClass(Validate.classes.inputError);

                this.$inputs.off('.validate');

                WG.unregisterComponent(this);
            }
        }]);

        return Validate;
    }();

    Validate.defaults = {
        validateOnChange: false
    };

    Validate.classes = {
        inputError: 'wg-input--invalid',
        fieldErrorSelector: '.wg-field-error',
        fieldErrorClass: 'wg-field-error--visible'
    };

    Validate.patterns = {
        'email-business': function emailBusiness(value) {
            // jscs:disable maximumLineLength
            var publicDomains = '163.com.21cn.com.2geton.net.aim.com.allstate.com.amfam.com.aol.com.att.net.bbtel.com.bellsouth.net.bigpond.com.bigstring.com.bk.ru.btinternet.com.cableone.net.care2.com.centurytel.net.citromail.com.comcast.net.cox.com.cox-internet.com.cts.com.dtccom.net.duo-county.com.earthlink.com.eatel.net.eyou.com.farmersagent.com.fastmail.fm.fdn.com.foothills.net.free.fr.freemail.ru.frognet.net.frontier.com.ftcweb.net.fuse.net.gawab.com.gmail.com.gmx.com.gmx.net.googlemail.com.gwt.net.horizonview.net.hotmail.com.hotpop.com.hrtc.net.i-55.com.icloud.com.iglou.com.inbox.com.inbox.ru.indamail.ru.insightbb.com.insuremail.net.isnetworking.com.jnxjn.com.kyol.net.lavabit.com.libero.it.list.ru.live.com.livecom.net.lycos.com.mac.com.mail.com.mail.ru.mailinator.com.maine.rr.com.mchsi.com.me.com.midcoast.com.msn.com.mviweb.com.myfairpoint.net.myspace.com.myway.com.nate.com.nationwide.com.naver.com.nomail.com.nwagent.com.o2.de.optilink.us.optonline.net.orange.com.orange.fr.outlook.com.privacyharbor.com.prodigy.net.mx.ptd.net.qq.com.rambler.ru.redshift.com.rocketmail.com.sbcglobal.com.scee.net.shopko.com.sina.com.sohu.com.statefarm.com.suddenlink.net.tds.net.telus.net.tempinbox.com.testmail.com.tkronline.com.tom.com.t-online.com.t-online.de.verizon.net.vipmail.com.wanadoo.com.web.com.webmail.ru.windowslive.com.windstream.net.ya.ru.yahoo.com.yandex.ru.yandex.com.yeah.com.yipple.com.ymail.com.yopmail.com.zapak.com.nicedriveway.com.hotmail.es.mpbio.com.hotmail.co.uk.123.com.123box.net.123india.com.123mail.cl.123qwe.co.uk.150ml.com.15meg4free.com.163.com.1coolplace.com.1freeemail.com.1funplace.com.1internetdrive.com.1mail.net.1me.net.1mum.com.1musicrow.com.1netdrive.com.1nsyncfan.com.1under.com.1webave.com.1webhighway.com.212.com.24horas.com.2911.net.2d2i.com.2die4.com.3000.it.37.com.3ammagazine.com.3email.com.3xl.net.444.net.4email.com.4email.net.4mg.com.4newyork.com.4x4man.com.5iron.com.88.am.8848.net.aaronkwok.net.abbeyroadlondon.co.uk.abdulnour.com.aberystwyth.com.about.com.academycougars.com.acceso.or.cr.access4less.net.accessgcc.com.ace-of-base.com.acmemail.net.acninc.net.adexec.com.adios.net.ados.fr.advalvas.be.aeiou.pt.aemail4u.com.aeneasmail.com.afreeinternet.com.africamail.com.agoodmail.com.ahaa.dk.aichi.com.airpost.net.ajacied.com.ak47.hu.aknet.kg.albawaba.com.alex4all.com.alexandria.cc.algeria.com.alhilal.net.alibaba.com.alive.cz.allmail.net.alloymail.com.allsaintsfan.com.alskens.dk.altavista.com.altavista.se.alternativagratis.com.alumnidirector.com.alvilag.hu.amele.com.america.hm.amnetsal.com.amrer.net.amuro.net.amuromail.com.ananzi.co.za.andylau.net.anfmail.com.angelfire.com.animalwoman.net.anjungcafe.com.another.com.antisocial.com.antongijsen.com.antwerpen.com.anymoment.com.anytimenow.com.apexmail.com.apollo.lv.approvers.net.arabia.com.arabtop.net.archaeologist.com.arcor.de.arcotronics.bg.argentina.com.arnet.com.ar.artlover.com.artlover.com.au.as-if.com.asean-mail.com.asheville.com.asia-links.com.asia.com.asianavenue.com.asiancityweb.com.asianwired.net.assala.com.assamesemail.com.astroboymail.com.astrolover.com.asurfer.com.athenachu.net.atina.cl.atl.lv.atlaswebmail.com.atozasia.com.au.ru.ausi.com.australia.edu.australiamail.com.austrosearch.net.autoescuelanerja.com.avh.hu.ayna.com.azimiweb.com.bachelorboy.com.bachelorgal.com.backstreet-boys.com.backstreetboysclub.com.bagherpour.com.baptistmail.com.baptized.com.barcelona.com.batuta.net.baudoinconsulting.com.bcvibes.com.beeebank.com.beenhad.com.beep.ru.beer.com.beethoven.com.belice.com.belizehome.com.berlin.com.berlin.de.berlinexpo.de.bestmail.us.bharatmail.com.bigblue.net.au.bigboab.com.bigfoot.com.bigfoot.de.bigger.com.bigmailbox.com.bigramp.com.bikemechanics.com.bikeracers.net.bikerider.com.bimla.net.birdowner.net.bitpage.net.bizhosting.com.blackburnmail.com.blackplanet.com.blazemail.com.bluehyppo.com.bluemail.ch.bluemail.dk.blushmail.com.bmlsports.net.boardermail.com.bol.com.br.bolando.com.bollywoodz.com.bolt.com.boltonfans.com.bonbon.net.boom.com.bootmail.com.bornnaked.com.bostonoffice.com.bounce.net.box.az.boxbg.com.boxemail.com.boxfrog.com.boyzoneclub.com.bradfordfans.com.brasilia.net.brazilmail.com.br.breathe.com.brfree.com.br.britneyclub.com.brittonsign.com.btopenworld.co.uk.bullsfan.com.bullsgame.com.bumerang.ro.buryfans.com.business-man.com.businessman.net.bvimailbox.com.c2i.net.c3.hu.c4.com.caere.it.cairomail.com.callnetuk.com.caltanet.it.camidge.com.canada-11.com.canada.com.canoemail.com.canwetalk.com.caramail.com.care2.com.carioca.net.cartestraina.ro.catcha.com.catlover.com.cd2.com.celineclub.com.centoper.it.centralpets.com.centrum.cz.centrum.sk.cgac.es.chaiyomail.com.chance2mail.com.chandrasekar.net.chat.ru.chattown.com.chauhanweb.com.check1check.com.cheerful.com.chemist.com.chequemail.com.chickmail.com.china.net.vg.chirk.com.chocaholic.com.au.cia-agent.com.cia.hu.ciaoweb.it.cicciociccio.com.city-of-bath.org.city-of-birmingham.com.city-of-brighton.org.city-of-cambridge.com.city-of-coventry.com.city-of-edinburgh.com.city-of-lichfield.com.city-of-lincoln.com.city-of-liverpool.com.city-of-manchester.com.city-of-nottingham.com.city-of-oxford.com.city-of-swansea.com.city-of-westminster.com.city-of-westminster.net.city-of-york.net.cityofcardiff.net.cityoflondon.org.claramail.com.classicmail.co.za.clerk.com.cliffhanger.com.close2you.net.club4x4.net.clubalfa.com.clubbers.net.clubducati.com.clubhonda.net.cluemail.com.coder.hu.coid.biz.columnist.com.comic.com.compuserve.com.computer-freak.com.computermail.net.conexcol.com.connect4free.net.connectbox.com.consultant.com.cookiemonster.com.cool.br.coolgoose.ca.coolgoose.com.coolkiwi.com.coollist.com.coolmail.com.coolmail.net.coolsend.com.cooooool.com.cooperation.net.cooperationtogo.net.copacabana.com.cornerpub.com.corporatedirtbag.com.correo.terra.com.gt.cortinet.com.cotas.net.counsellor.com.countrylover.com.cracker.hu.crazedanddazed.com.crazysexycool.com.critterpost.com.croeso.com.crosswinds.net.cry4helponline.com.cs.com.csinibaba.hu.curio-city.com.cute-girl.com.cuteandcuddly.com.cutey.com.cww.de.cyberbabies.com.cyberforeplay.net.cyberinbox.com.cyberleports.com.cybernet.it.dabsol.net.dadacasa.com.dailypioneer.com.dangerous-minds.com.dansegulvet.com.data54.com.davegracey.com.dazedandconfused.com.dbzmail.com.dcemail.com.deadlymob.org.deal-maker.com.dearriba.com.death-star.com.deliveryman.com.desertmail.com.desilota.com.deskpilot.com.detik.com.devotedcouples.com.dfwatson.com.di-ve.com.diplomats.com.disinfo.net.dmailman.com.dnsmadeeasy.com.doctor.com.doglover.com.dogmail.co.uk.dogsnob.net.doityourself.com.doneasy.com.donjuan.com.dontgotmail.com.dontmesswithtexas.com.doramail.com.dostmail.com.dotcom.fr.dott.it.dplanet.ch.dr.com.dragoncon.net.dropzone.com.dubaimail.com.dublin.com.dublin.ie.dygo.com.dynamitemail.com.e-apollo.lv.e-mail.dk.e-mail.ru.e-mailanywhere.com.e-mails.ru.e-tapaal.com.earthalliance.com.earthdome.com.eastcoast.co.za.eastmail.com.ecbsolutions.net.echina.com.ednatx.com.educacao.te.pt.eircom.net.elsitio.com.elvis.com.email-london.co.uk.email.com.email.cz.email.ee.email.it.email.nu.email.ro.email.ru.email.si.email2me.net.emailacc.com.emailaccount.com.emailchoice.com.emailcorner.net.emailengine.net.emailforyou.net.emailgroups.net.emailpinoy.com.emailplanet.com.emails.ru.emailuser.net.emailx.net.ematic.com.end-war.com.enel.net.engineer.com.england.com.england.edu.epatra.com.epost.de.eposta.hu.eqqu.com.eramail.co.za.eresmas.com.eriga.lv.estranet.it.etoast.com.eudoramail.com.europe.com.euroseek.com.every1.net.everyday.com.kh.everyone.net.examnotes.net.excite.co.jp.excite.com.excite.it.execs.com.expressasia.com.extended.com.eyou.com.ezcybersearch.com.ezmail.egine.com.ezmail.ru.ezrs.com.f1fans.net.fantasticmail.com.faroweb.com.fastem.com.fastemail.us.fastemailer.com.fastermail.com.fastimap.com.fastmail.fm.fastmailbox.net.fastmessaging.com.fatcock.net.fathersrightsne.org.fbi-agent.com.fbi.hu.federalcontractors.com.femenino.com.feyenoorder.com.ffanet.com.fiberia.com.filipinolinks.com.financemail.net.financier.com.findmail.com.finebody.com.fire-brigade.com.fishburne.org.flashmail.com.flipcode.com.fmail.co.uk.fmailbox.com.fmgirl.com.fmguy.com.fnbmail.co.za.fnmail.com.for-president.com.forfree.at.forpresident.com.fortuncity.com.forum.dk.free.com.pe.free.fr.freeaccess.nl.freeandsingle.com.freedomlover.com.freegates.be.freeghana.com.freeler.nl.freemail.com.au.freemail.com.pk.freemail.de.freemail.et.freemail.gr.freemail.hu.freemail.it.freemail.lt.freemail.nl.freemail.org.mk.freenet.de.freenet.kg.freeola.com.freeola.net.freeserve.co.uk.freestart.hu.freesurf.fr.freesurf.nl.freeuk.com.freeuk.net.freeukisp.co.uk.freeweb.org.freewebemail.com.freeyellow.com.freezone.co.uk.fresnomail.com.friendsfan.com.from-africa.com.from-america.com.from-argentina.com.from-asia.com.from-australia.com.from-belgium.com.from-brazil.com.from-canada.com.from-china.net.from-england.com.from-europe.com.from-france.net.from-germany.net.from-holland.com.from-israel.com.from-italy.net.from-japan.net.from-korea.com.from-mexico.com.from-outerspace.com.from-russia.com.from-spain.net.fromalabama.com.fromalaska.com.fromarizona.com.fromarkansas.com.fromcalifornia.com.fromcolorado.com.fromconnecticut.com.fromdelaware.com.fromflorida.net.fromgeorgia.com.fromhawaii.net.fromidaho.com.fromillinois.com.fromindiana.com.fromiowa.com.fromjupiter.com.fromkansas.com.fromkentucky.com.fromlouisiana.com.frommaine.net.frommaryland.com.frommassachusetts.com.frommiami.com.frommichigan.com.fromminnesota.com.frommississippi.com.frommissouri.com.frommontana.com.fromnebraska.com.fromnevada.com.fromnewhampshire.com.fromnewjersey.com.fromnewmexico.com.fromnewyork.net.fromnorthcarolina.com.fromnorthdakota.com.fromohio.com.fromoklahoma.com.fromoregon.net.frompennsylvania.com.fromrhodeisland.com.fromru.com.fromsouthcarolina.com.fromsouthdakota.com.fromtennessee.com.fromtexas.com.fromthestates.com.fromutah.com.fromvermont.com.fromvirginia.com.fromwashington.com.fromwashingtondc.com.fromwestvirginia.com.fromwisconsin.com.fromwyoming.com.front.ru.frostbyte.uk.net.fsmail.net.ftml.net.fuorissimo.com.furnitureprovider.com.fut.es.fxsmails.com.galaxy5.com.gamebox.net.gardener.com.gawab.com.gaza.net.gazeta.pl.gazibooks.com.geek.hu.geeklife.com.general-hospital.com.geologist.com.geopia.com.giga4u.de.givepeaceachance.com.glay.org.glendale.net.globalfree.it.globalpagan.com.globalsite.com.br.gmx.at.gmx.de.gmx.li.gmx.net.go.com.go.ro.go.ru.go2net.com.gofree.co.uk.goldenmail.ru.goldmail.ru.golfemail.com.golfmail.be.goplay.com.gorontalo.net.gothere.uk.com.gotmail.com.gotomy.com.gportal.hu.graffiti.net.gratisweb.com.grungecafe.com.gua.net.guessmail.com.guju.net.guy.com.guy2.com.guyanafriends.com.gyorsposta.com.gyorsposta.hu.hackermail.net.hailmail.net.hairdresser.net.hamptonroads.com.handbag.com.hang-ten.com.happemail.com.happycounsel.com.hardcorefreak.com.heartthrob.com.heerschap.com.heesun.net.hehe.com.hello.hu.helter-skelter.com.herediano.com.herono1.com.highmilton.com.highquality.com.highveldmail.co.za.hispavista.com.hkstarphoto.com.hollywoodkids.com.home.no.net.home.ro.home.se.homelocator.com.homestead.com.hongkong.com.hookup.net.horrormail.com.hot-shot.com.hot.ee.hotbot.com.hotbrev.com.hotfire.net.hotletter.com.hotmail.co.il.hotmail.com.hotmail.fr.hotmail.kg.hotmail.kz.hotmail.ru.hotpop.com.hotpop3.com.hotvoice.com.hsuchi.net.hunsa.com.hushmail.com.i-france.com.i-mail.com.au.i-p.com.i12.com.iamawoman.com.iamwaiting.com.iamwasted.com.iamyours.com.icestorm.com.icmsconsultants.com.icq.com.icqmail.com.icrazy.com.ididitmyway.com.idirect.com.iespana.es.ignazio.it.ignmail.com.ijustdontcare.com.ilovechocolate.com.ilovetocollect.net.ilse.nl.imail.ru.imailbox.com.imel.org.imneverwrong.com.imposter.co.uk.imstressed.com.imtoosexy.com.in-box.net.iname.com.inbox.net.inbox.ru.incamail.com.incredimail.com.indexa.fr.india.com.indiatimes.com.infohq.com.infomail.es.infomart.or.jp.infovia.com.ar.inicia.es.inmail.sk.inorbit.com.insurer.com.interfree.it.interia.pl.interlap.com.ar.intermail.co.il.internet-police.com.internetbiz.com.internetdrive.com.internetegypt.com.internetemails.net.internetmailing.net.inwind.it.iobox.com.iobox.fi.iol.it.ip3.com.iqemail.com.irangate.net.iraqmail.com.irj.hu.isellcars.com.islamonline.net.ismart.net.isonfire.com.isp9.net.itloox.com.itmom.com.ivebeenframed.com.ivillage.com.iwan-fals.com.iwon.com.izadpanah.com.jakuza.hu.japan.com.jaydemail.com.jazzandjava.com.jazzgame.com.jetemail.net.jippii.fi.jmail.co.za.joinme.com.jordanmail.com.journalist.com.jovem.te.pt.joymail.com.jpopmail.com.jubiimail.dk.jumpy.it.juno.com.justemail.net.kaazoo.com.kaixo.com.kalpoint.com.kapoorweb.com.karachian.com.karachioye.com.karbasi.com.katamail.com.kayafmmail.co.za.keg-party.com.keko.com.ar.kellychen.com.keromail.com.kgb.hu.khosropour.com.kickassmail.com.killermail.com.kimo.com.kinki-kids.com.kittymail.com.kiwibox.com.kiwitown.com.krunis.com.kukamail.com.kumarweb.com.kuwait-mail.com.ladymail.cz.lagerlouts.com.lahoreoye.com.lakmail.com.lamer.hu.land.ru.lankamail.com.laposte.net.latinmail.com.lawyer.com.leehom.net.legalactions.com.legislator.com.leonlai.net.levele.com.levele.hu.lex.bg.liberomail.com.linkmaster.com.linuxfreemail.com.linuxmail.org.lionsfan.com.au.liontrucks.com.list.ru.liverpoolfans.com.llandudno.com.llangollen.com.lmxmail.sk.lobbyist.com.localbar.com.london.com.looksmart.co.uk.looksmart.com.looksmart.com.au.lopezclub.com.louiskoo.com.love.cz.loveable.com.lovelygirl.net.lovemail.com.lover-boy.com.lovergirl.com.lovingjesus.com.luso.pt.luukku.com.lycos.co.uk.lycos.com.lycos.es.lycos.it.lycos.ne.jp.lycosmail.com.m-a-i-l.com.mac.com.machinecandy.com.macmail.com.madrid.com.maffia.hu.magicmail.co.za.mahmoodweb.com.mail-awu.de.mail-box.cz.mail-center.com.mail-central.com.mail-page.com.mail.austria.com.mail.az.mail.be.mail.bulgaria.com.mail.co.za.mail.com.mail.ee.mail.gr.mail.md.mail.nu.mail.pf.mail.pt.mail.r-o-o-t.com.mail.ru.mail.sisna.com.mail.vasarhely.hu.mail15.com.mail2007.com.mail2aaron.com.mail2abby.com.mail2abc.com.mail2actor.com.mail2admiral.com.mail2adorable.com.mail2adoration.com.mail2adore.com.mail2adventure.com.mail2aeolus.com.mail2aether.com.mail2affection.com.mail2afghanistan.com.mail2africa.com.mail2agent.com.mail2aha.com.mail2ahoy.com.mail2aim.com.mail2air.com.mail2airbag.com.mail2airforce.com.mail2airport.com.mail2alabama.com.mail2alan.com.mail2alaska.com.mail2albania.com.mail2alcoholic.com.mail2alec.com.mail2alexa.com.mail2algeria.com.mail2alicia.com.mail2alien.com.mail2allan.com.mail2allen.com.mail2allison.com.mail2alpha.com.mail2alyssa.com.mail2amanda.com.mail2amazing.com.mail2amber.com.mail2america.com.mail2american.com.mail2andorra.com.mail2andrea.com.mail2andy.com.mail2anesthesiologist.com.mail2angela.com.mail2angola.com.mail2ann.com.mail2anna.com.mail2anne.com.mail2anthony.com.mail2anything.com.mail2aphrodite.com.mail2apollo.com.mail2april.com.mail2aquarius.com.mail2arabia.com.mail2arabic.com.mail2architect.com.mail2ares.com.mail2argentina.com.mail2aries.com.mail2arizona.com.mail2arkansas.com.mail2armenia.com.mail2army.com.mail2arnold.com.mail2art.com.mail2artemus.com.mail2arthur.com.mail2artist.com.mail2ashley.com.mail2ask.com.mail2astronomer.com.mail2athena.com.mail2athlete.com.mail2atlas.com.mail2atom.com.mail2attitude.com.mail2auction.com.mail2aunt.com.mail2australia.com.mail2austria.com.mail2azerbaijan.com.mail2baby.com.mail2bahamas.com.mail2bahrain.com.mail2ballerina.com.mail2ballplayer.com.mail2band.com.mail2bangladesh.com.mail2bank.com.mail2banker.com.mail2bankrupt.com.mail2baptist.com.mail2bar.com.mail2barbados.com.mail2barbara.com.mail2barter.com.mail2basketball.com.mail2batter.com.mail2beach.com.mail2beast.com.mail2beatles.com.mail2beauty.com.mail2becky.com.mail2beijing.com.mail2belgium.com.mail2belize.com.mail2ben.com.mail2bernard.com.mail2beth.com.mail2betty.com.mail2beverly.com.mail2beyond.com.mail2biker.com.mail2bill.com.mail2billionaire.com.mail2billy.com.mail2bio.com.mail2biologist.com.mail2black.com.mail2blackbelt.com.mail2blake.com.mail2blind.com.mail2blonde.com.mail2blues.com.mail2bob.com.mail2bobby.com.mail2bolivia.com.mail2bombay.com.mail2bonn.com.mail2bookmark.com.mail2boreas.com.mail2bosnia.com.mail2boston.com.mail2botswana.com.mail2bradley.com.mail2brazil.com.mail2breakfast.com.mail2brian.com.mail2bride.com.mail2brittany.com.mail2broker.com.mail2brook.com.mail2bruce.com.mail2brunei.com.mail2brunette.com.mail2brussels.com.mail2bryan.com.mail2bug.com.mail2bulgaria.com.mail2business.com.mail2buy.com.mail2ca.com.mail2california.com.mail2calvin.com.mail2cambodia.com.mail2cameroon.com.mail2canada.com.mail2cancer.com.mail2capeverde.com.mail2capricorn.com.mail2cardinal.com.mail2cardiologist.com.mail2care.com.mail2caroline.com.mail2carolyn.com.mail2casey.com.mail2cat.com.mail2caterer.com.mail2cathy.com.mail2catlover.com.mail2catwalk.com.mail2cell.com.mail2chad.com.mail2champaign.com.mail2charles.com.mail2chef.com.mail2chemist.com.mail2cherry.com.mail2chicago.com.mail2chile.com.mail2china.com.mail2chinese.com.mail2chocolate.com.mail2christian.com.mail2christie.com.mail2christmas.com.mail2christy.com.mail2chuck.com.mail2cindy.com.mail2clark.com.mail2classifieds.com.mail2claude.com.mail2cliff.com.mail2clinic.com.mail2clint.com.mail2close.com.mail2club.com.mail2coach.com.mail2coastguard.com.mail2colin.com.mail2college.com.mail2colombia.com.mail2color.com.mail2colorado.com.mail2columbia.com.mail2comedian.com.mail2composer.com.mail2computer.com.mail2computers.com.mail2concert.com.mail2congo.com.mail2connect.com.mail2connecticut.com.mail2consultant.com.mail2convict.com.mail2cook.com.mail2cool.com.mail2cory.com.mail2costarica.com.mail2country.com.mail2courtney.com.mail2cowboy.com.mail2cowgirl.com.mail2craig.com.mail2crave.com.mail2crazy.com.mail2create.com.mail2croatia.com.mail2cry.com.mail2crystal.com.mail2cuba.com.mail2culture.com.mail2curt.com.mail2customs.com.mail2cute.com.mail2cutey.com.mail2cynthia.com.mail2cyprus.com.mail2czechrepublic.com.mail2dad.com.mail2dale.com.mail2dallas.com.mail2dan.com.mail2dana.com.mail2dance.com.mail2dancer.com.mail2danielle.com.mail2danny.com.mail2darlene.com.mail2darling.com.mail2darren.com.mail2daughter.com.mail2dave.com.mail2dawn.com.mail2dc.com.mail2dealer.com.mail2deanna.com.mail2dearest.com.mail2debbie.com.mail2debby.com.mail2deer.com.mail2delaware.com.mail2delicious.com.mail2demeter.com.mail2democrat.com.mail2denise.com.mail2denmark.com.mail2dennis.com.mail2dentist.com.mail2derek.com.mail2desert.com.mail2devoted.com.mail2devotion.com.mail2diamond.com.mail2diana.com.mail2diane.com.mail2diehard.com.mail2dilemma.com.mail2dillon.com.mail2dinner.com.mail2dinosaur.com.mail2dionysos.com.mail2diplomat.com.mail2director.com.mail2dirk.com.mail2disco.com.mail2dive.com.mail2diver.com.mail2divorced.com.mail2djibouti.com.mail2doctor.com.mail2doglover.com.mail2dominic.com.mail2dominica.com.mail2dominicanrepublic.com.mail2don.com.mail2donald.com.mail2donna.com.mail2doris.com.mail2dorothy.com.mail2doug.com.mail2dough.com.mail2douglas.com.mail2dow.com.mail2downtown.com.mail2dream.com.mail2dreamer.com.mail2dude.com.mail2dustin.com.mail2dyke.com.mail2dylan.com.mail2earl.com.mail2earth.com.mail2eastend.com.mail2eat.com.mail2economist.com.mail2ecuador.com.mail2eddie.com.mail2edgar.com.mail2edwin.com.mail2egypt.com.mail2electron.com.mail2eli.com.mail2elizabeth.com.mail2ellen.com.mail2elliot.com.mail2elsalvador.com.mail2elvis.com.mail2emergency.com.mail2emily.com.mail2engineer.com.mail2english.com.mail2environmentalist.com.mail2eos.com.mail2eric.com.mail2erica.com.mail2erin.com.mail2erinyes.com.mail2eris.com.mail2eritrea.com.mail2ernie.com.mail2eros.com.mail2estonia.com.mail2ethan.com.mail2ethiopia.com.mail2eu.com.mail2europe.com.mail2eurus.com.mail2eva.com.mail2evan.com.mail2evelyn.com.mail2everything.com.mail2exciting.com.mail2expert.com.mail2fairy.com.mail2faith.com.mail2fanatic.com.mail2fancy.com.mail2fantasy.com.mail2farm.com.mail2farmer.com.mail2fashion.com.mail2fat.com.mail2feeling.com.mail2female.com.mail2fever.com.mail2fighter.com.mail2fiji.com.mail2filmfestival.com.mail2films.com.mail2finance.com.mail2finland.com.mail2fireman.com.mail2firm.com.mail2fisherman.com.mail2flexible.com.mail2florence.com.mail2florida.com.mail2floyd.com.mail2fly.com.mail2fond.com.mail2fondness.com.mail2football.com.mail2footballfan.com.mail2found.com.mail2france.com.mail2frank.com.mail2frankfurt.com.mail2franklin.com.mail2fred.com.mail2freddie.com.mail2free.com.mail2freedom.com.mail2french.com.mail2freudian.com.mail2friendship.com.mail2from.com.mail2fun.com.mail2gabon.com.mail2gabriel.com.mail2gail.com.mail2galaxy.com.mail2gambia.com.mail2games.com.mail2gary.com.mail2gavin.com.mail2gemini.com.mail2gene.com.mail2genes.com.mail2geneva.com.mail2george.com.mail2georgia.com.mail2gerald.com.mail2german.com.mail2germany.com.mail2ghana.com.mail2gilbert.com.mail2gina.com.mail2girl.com.mail2glen.com.mail2gloria.com.mail2goddess.com.mail2gold.com.mail2golfclub.com.mail2golfer.com.mail2gordon.com.mail2government.com.mail2grab.com.mail2grace.com.mail2graham.com.mail2grandma.com.mail2grandpa.com.mail2grant.com.mail2greece.com.mail2green.com.mail2greg.com.mail2grenada.com.mail2gsm.com.mail2guard.com.mail2guatemala.com.mail2guy.com.mail2hades.com.mail2haiti.com.mail2hal.com.mail2handhelds.com.mail2hank.com.mail2hannah.com.mail2harold.com.mail2harry.com.mail2hawaii.com.mail2headhunter.com.mail2heal.com.mail2heather.com.mail2heaven.com.mail2hebe.com.mail2hecate.com.mail2heidi.com.mail2helen.com.mail2hell.com.mail2help.com.mail2helpdesk.com.mail2henry.com.mail2hephaestus.com.mail2hera.com.mail2hercules.com.mail2herman.com.mail2hermes.com.mail2hespera.com.mail2hestia.com.mail2highschool.com.mail2hindu.com.mail2hip.com.mail2hiphop.com.mail2holland.com.mail2holly.com.mail2hollywood.com.mail2homer.com.mail2honduras.com.mail2honey.com.mail2hongkong.com.mail2hope.com.mail2horse.com.mail2hot.com.mail2hotel.com.mail2houston.com.mail2howard.com.mail2hugh.com.mail2human.com.mail2hungary.com.mail2hungry.com.mail2hygeia.com.mail2hyperspace.com.mail2hypnos.com.mail2ian.com.mail2ice-cream.com.mail2iceland.com.mail2idaho.com.mail2idontknow.com.mail2illinois.com.mail2imam.com.mail2in.com.mail2india.com.mail2indian.com.mail2indiana.com.mail2indonesia.com.mail2infinity.com.mail2intense.com.mail2iowa.com.mail2iran.com.mail2iraq.com.mail2ireland.com.mail2irene.com.mail2iris.com.mail2irresistible.com.mail2irving.com.mail2irwin.com.mail2isaac.com.mail2israel.com.mail2italian.com.mail2italy.com.mail2jackie.com.mail2jacob.com.mail2jail.com.mail2jaime.com.mail2jake.com.mail2jamaica.com.mail2james.com.mail2jamie.com.mail2jan.com.mail2jane.com.mail2janet.com.mail2janice.com.mail2japan.com.mail2japanese.com.mail2jasmine.com.mail2jason.com.mail2java.com.mail2jay.com.mail2jazz.com.mail2jed.com.mail2jeffrey.com.mail2jennifer.com.mail2jenny.com.mail2jeremy.com.mail2jerry.com.mail2jessica.com.mail2jessie.com.mail2jesus.com.mail2jew.com.mail2jeweler.com.mail2jim.com.mail2jimmy.com.mail2joan.com.mail2joann.com.mail2joanna.com.mail2jody.com.mail2joe.com.mail2joel.com.mail2joey.com.mail2john.com.mail2join.com.mail2jon.com.mail2jonathan.com.mail2jones.com.mail2jordan.com.mail2joseph.com.mail2josh.com.mail2joy.com.mail2juan.com.mail2judge.com.mail2judy.com.mail2juggler.com.mail2julian.com.mail2julie.com.mail2jumbo.com.mail2junk.com.mail2justin.com.mail2justme.com.mail2kansas.com.mail2karate.com.mail2karen.com.mail2karl.com.mail2karma.com.mail2kathleen.com.mail2kathy.com.mail2katie.com.mail2kay.com.mail2kazakhstan.com.mail2keen.com.mail2keith.com.mail2kelly.com.mail2kelsey.com.mail2ken.com.mail2kendall.com.mail2kennedy.com.mail2kenneth.com.mail2kenny.com.mail2kentucky.com.mail2kenya.com.mail2kerry.com.mail2kevin.com.mail2kim.com.mail2kimberly.com.mail2king.com.mail2kirk.com.mail2kiss.com.mail2kosher.com.mail2kristin.com.mail2kurt.com.mail2kuwait.com.mail2kyle.com.mail2kyrgyzstan.com.mail2la.com.mail2lacrosse.com.mail2lance.com.mail2lao.com.mail2larry.com.mail2latvia.com.mail2laugh.com.mail2laura.com.mail2lauren.com.mail2laurie.com.mail2lawrence.com.mail2lawyer.com.mail2lebanon.com.mail2lee.com.mail2leo.com.mail2leon.com.mail2leonard.com.mail2leone.com.mail2leslie.com.mail2letter.com.mail2liberia.com.mail2libertarian.com.mail2libra.com.mail2libya.com.mail2liechtenstein.com.mail2life.com.mail2linda.com.mail2linux.com.mail2lionel.com.mail2lipstick.com.mail2liquid.com.mail2lisa.com.mail2lithuania.com.mail2litigator.com.mail2liz.com.mail2lloyd.com.mail2lois.com.mail2lola.com.mail2london.com.mail2looking.com.mail2lori.com.mail2lost.com.mail2lou.com.mail2louis.com.mail2louisiana.com.mail2lovable.com.mail2love.com.mail2lucky.com.mail2lucy.com.mail2lunch.com.mail2lust.com.mail2luxembourg.com.mail2luxury.com.mail2lyle.com.mail2lynn.com.mail2madagascar.com.mail2madison.com.mail2madrid.com.mail2maggie.com.mail2mail4.com.mail2maine.com.mail2malawi.com.mail2malaysia.com.mail2maldives.com.mail2mali.com.mail2malta.com.mail2mambo.com.mail2man.com.mail2mandy.com.mail2manhunter.com.mail2mankind.com.mail2many.com.mail2marc.com.mail2marcia.com.mail2margaret.com.mail2margie.com.mail2marhaba.com.mail2maria.com.mail2marilyn.com.mail2marines.com.mail2mark.com.mail2marriage.com.mail2married.com.mail2marries.com.mail2mars.com.mail2marsha.com.mail2marshallislands.com.mail2martha.com.mail2martin.com.mail2marty.com.mail2marvin.com.mail2mary.com.mail2maryland.com.mail2mason.com.mail2massachusetts.com.mail2matt.com.mail2matthew.com.mail2maurice.com.mail2mauritania.com.mail2mauritius.com.mail2max.com.mail2maxwell.com.mail2maybe.com.mail2mba.com.mail2me4u.com.mail2mechanic.com.mail2medieval.com.mail2megan.com.mail2mel.com.mail2melanie.com.mail2melissa.com.mail2melody.com.mail2member.com.mail2memphis.com.mail2methodist.com.mail2mexican.com.mail2mexico.com.mail2mgz.com.mail2miami.com.mail2michael.com.mail2michelle.com.mail2michigan.com.mail2mike.com.mail2milan.com.mail2milano.com.mail2mildred.com.mail2milkyway.com.mail2millennium.com.mail2millionaire.com.mail2milton.com.mail2mime.com.mail2mindreader.com.mail2mini.com.mail2minister.com.mail2minneapolis.com.mail2minnesota.com.mail2miracle.com.mail2missionary.com.mail2mississippi.com.mail2missouri.com.mail2mitch.com.mail2model.com.mail2moldova.commail2molly.com.mail2mom.com.mail2monaco.com.mail2money.com.mail2mongolia.com.mail2monica.com.mail2montana.com.mail2monty.com.mail2moon.com.mail2morocco.com.mail2morpheus.com.mail2mors.com.mail2moscow.com.mail2moslem.com.mail2mouseketeer.com.mail2movies.com.mail2mozambique.com.mail2mp3.com.mail2mrright.com.mail2msright.com.mail2museum.com.mail2music.com.mail2musician.com.mail2muslim.com.mail2my.com.mail2myboat.com.mail2mycar.com.mail2mycell.com.mail2mygsm.com.mail2mylaptop.com.mail2mymac.com.mail2mypager.com.mail2mypalm.com.mail2mypc.com.mail2myphone.com.mail2myplane.com.mail2namibia.com.mail2nancy.com.mail2nasdaq.com.mail2nathan.com.mail2nauru.com.mail2navy.com.mail2neal.com.mail2nebraska.com.mail2ned.com.mail2neil.com.mail2nelson.com.mail2nemesis.com.mail2nepal.com.mail2netherlands.com.mail2network.com.mail2nevada.com.mail2newhampshire.com.mail2newjersey.com.mail2newmexico.com.mail2newyork.com.mail2newzealand.com.mail2nicaragua.com.mail2nick.com.mail2nicole.com.mail2niger.com.mail2nigeria.com.mail2nike.com.mail2no.com.mail2noah.com.mail2noel.com.mail2noelle.com.mail2normal.com.mail2norman.com.mail2northamerica.com.mail2northcarolina.com.mail2northdakota.com.mail2northpole.com.mail2norway.com.mail2notus.com.mail2noway.com.mail2nowhere.com.mail2nuclear.com.mail2nun.com.mail2ny.com.mail2oasis.com.mail2oceanographer.com.mail2ohio.com.mail2ok.com.mail2oklahoma.com.mail2oliver.com.mail2oman.com.mail2one.com.mail2onfire.com.mail2online.com.mail2oops.com.mail2open.com.mail2ophthalmologist.com.mail2optometrist.com.mail2oregon.com.mail2oscars.com.mail2oslo.com.mail2painter.com.mail2pakistan.com.mail2palau.com.mail2pan.com.mail2panama.com.mail2paraguay.com.mail2paralegal.com.mail2paris.com.mail2park.com.mail2parker.com.mail2party.com.mail2passion.com.mail2pat.com.mail2patricia.com.mail2patrick.com.mail2patty.com.mail2paul.com.mail2paula.com.mail2pay.com.mail2peace.com.mail2pediatrician.com.mail2peggy.com.mail2pennsylvania.com.mail2perry.com.mail2persephone.com.mail2persian.com.mail2peru.com.mail2pete.com.mail2peter.com.mail2pharmacist.com.mail2phil.com.mail2philippines.com.mail2phoenix.com.mail2phonecall.com.mail2phyllis.com.mail2pickup.com.mail2pilot.com.mail2pisces.com.mail2planet.com.mail2platinum.com.mail2plato.com.mail2pluto.com.mail2pm.com.mail2podiatrist.com.mail2poet.com.mail2poland.com.mail2policeman.com.mail2policewoman.com.mail2politician.com.mail2pop.com.mail2pope.com.mail2popular.com.mail2portugal.com.mail2poseidon.com.mail2potatohead.com.mail2power.com.mail2presbyterian.com.mail2president.com.mail2priest.com.mail2prince.com.mail2princess.com.mail2producer.com.mail2professor.com.mail2protect.com.mail2psychiatrist.com.mail2psycho.com.mail2psychologist.com.mail2qatar.com.mail2queen.com.mail2rabbi.com.mail2race.com.mail2racer.com.mail2rachel.com.mail2rage.com.mail2rainmaker.com.mail2ralph.com.mail2randy.com.mail2rap.com.mail2rare.com.mail2rave.com.mail2ray.com.mail2raymond.com.mail2realtor.com.mail2rebecca.com.mail2recruiter.com.mail2recycle.com.mail2redhead.com.mail2reed.com.mail2reggie.com.mail2register.com.mail2rent.com.mail2republican.com.mail2resort.com.mail2rex.com.mail2rhodeisland.com.mail2rich.com.mail2richard.com.mail2ricky.com.mail2ride.com.mail2riley.com.mail2rita.com.mail2rob.com.mail2robert.com.mail2roberta.com.mail2robin.com.mail2rock.com.mail2rocker.com.mail2rod.com.mail2rodney.com.mail2romania.com.mail2rome.com.mail2ron.com.mail2ronald.com.mail2ronnie.com.mail2rose.com.mail2rosie.com.mail2roy.com.mail2rudy.com.mail2rugby.com.mail2runner.com.mail2russell.com.mail2russia.com.mail2russian.com.mail2rusty.com.mail2ruth.com.mail2rwanda.com.mail2ryan.com.mail2sa.com.mail2sabrina.com.mail2safe.com.mail2sagittarius.com.mail2sail.com.mail2sailor.com.mail2sal.com.mail2salaam.com.mail2sam.com.mail2samantha.com.mail2samoa.com.mail2samurai.com.mail2sandra.com.mail2sandy.com.mail2sanfrancisco.com.mail2sanmarino.com.mail2santa.com.mail2sara.com.mail2sarah.com.mail2sat.com.mail2saturn.com.mail2saudi.com.mail2saudiarabia.com.mail2save.com.mail2savings.com.mail2school.com.mail2scientist.com.mail2scorpio.com.mail2scott.com.mail2sean.com.mail2search.com.mail2seattle.com.mail2secretagent.com.mail2senate.com.mail2senegal.com.mail2sensual.com.mail2seth.com.mail2sevenseas.com.mail2sexy.com.mail2seychelles.com.mail2shane.com.mail2sharon.com.mail2shawn.com.mail2ship.com.mail2shirley.com.mail2shoot.com.mail2shuttle.com.mail2sierraleone.com.mail2simon.com.mail2singapore.com.mail2single.com.mail2site.com.mail2skater.com.mail2skier.com.mail2sky.com.mail2sleek.com.mail2slim.com.mail2slovakia.com.mail2slovenia.com.mail2smile.com.mail2smith.com.mail2smooth.com.mail2soccer.com.mail2soccerfan.com.mail2socialist.com.mail2soldier.com.mail2somalia.com.mail2son.com.mail2song.com.mail2sos.com.mail2sound.com.mail2southafrica.com.mail2southamerica.com.mail2southcarolina.com.mail2southdakota.com.mail2southkorea.com.mail2southpole.com.mail2spain.com.mail2spanish.com.mail2spare.com.mail2spectrum.com.mail2splash.com.mail2sponsor.com.mail2sports.com.mail2srilanka.com.mail2stacy.com.mail2stan.com.mail2stanley.com.mail2star.com.mail2state.com.mail2stephanie.com.mail2steve.com.mail2steven.com.mail2stewart.com.mail2stlouis.com.mail2stock.com.mail2stockholm.com.mail2stockmarket.com.mail2storage.com.mail2store.com.mail2strong.com.mail2student.com.mail2studio.com.mail2studio54.com.mail2stuntman.com.mail2subscribe.com.mail2sudan.com.mail2superstar.com.mail2surfer.com.mail2suriname.com.mail2susan.com.mail2suzie.com.mail2swaziland.com.mail2sweden.com.mail2sweetheart.com.mail2swim.com.mail2swimmer.com.mail2swiss.com.mail2switzerland.com.mail2sydney.com.mail2sylvia.com.mail2syria.com.mail2taboo.com.mail2taiwan.com.mail2tajikistan.com.mail2tammy.com.mail2tango.com.mail2tanya.com.mail2tanzania.com.mail2tara.com.mail2taurus.com.mail2taxi.com.mail2taxidermist.com.mail2taylor.com.mail2taz.com.mail2teacher.com.mail2technician.com.mail2ted.com.mail2telephone.com.mail2teletubbie.com.mail2tenderness.com.mail2tennessee.com.mail2tennis.com.mail2tennisfan.com.mail2terri.com.mail2terry.com.mail2test.com.mail2texas.com.mail2thailand.com.mail2therapy.com.mail2think.com.mail2tickets.com.mail2tiffany.com.mail2tim.com.mail2time.com.mail2timothy.com.mail2tina.com.mail2titanic.com.mail2toby.com.mail2todd.com.mail2togo.com.mail2tom.com.mail2tommy.com.mail2tonga.com.mail2tony.com.mail2touch.com.mail2tourist.com.mail2tracey.com.mail2tracy.com.mail2tramp.com.mail2travel.com.mail2traveler.com.mail2travis.com.mail2trekkie.com.mail2trex.com.mail2triallawyer.com.mail2trick.com.mail2trillionaire.com.mail2troy.com.mail2truck.com.mail2trump.com.mail2try.com.mail2tunisia.com.mail2turbo.com.mail2turkey.com.mail2turkmenistan.com.mail2tv.com.mail2tycoon.com.mail2tyler.com.mail2u4me.com.mail2uae.com.mail2uganda.com.mail2uk.com.mail2ukraine.com.mail2uncle.com.mail2unsubscribe.com.mail2uptown.com.mail2uruguay.com.mail2usa.com.mail2utah.com.mail2uzbekistan.com.mail2v.com.mail2vacation.com.mail2valentines.com.mail2valerie.com.mail2valley.com.mail2vamoose.com.mail2vanessa.com.mail2vanuatu.com.mail2venezuela.com.mail2venous.com.mail2venus.com.mail2vermont.com.mail2vickie.com.mail2victor.com.mail2victoria.com.mail2vienna.com.mail2vietnam.com.mail2vince.com.mail2virginia.com.mail2virgo.com.mail2visionary.com.mail2vodka.com.mail2volleyball.com.mail2waiter.com.mail2wallstreet.com.mail2wally.com.mail2walter.com.mail2warren.com.mail2washington.com.mail2wave.com.mail2way.com.mail2waycool.com.mail2wayne.com.mail2webmaster.com.mail2webtop.com.mail2webtv.com.mail2weird.com.mail2wendell.com.mail2wendy.com.mail2westend.com.mail2westvirginia.com.mail2whether.com.mail2whip.com.mail2white.com.mail2whitehouse.com.mail2whitney.com.mail2why.com.mail2wilbur.com.mail2wild.com.mail2willard.com.mail2willie.com.mail2wine.com.mail2winner.com.mail2wired.com.mail2wisconsin.com.mail2woman.com.mail2wonder.com.mail2world.com.mail2worship.com.mail2wow.com.mail2www.com.mail2wyoming.com.mail2xfiles.com.mail2xox.com.mail2yachtclub.com.mail2yahalla.com.mail2yemen.com.mail2yes.com.mail2yugoslavia.com.mail2zack.com.mail2zambia.com.mail2zenith.com.mail2zephir.com.mail2zeus.com.mail2zipper.com.mail2zoo.com.mail2zoologist.com.mail2zurich.com.mail3000.com.mail333.com.mailandftp.com.mailandnews.com.mailas.com.mailasia.com.mailbolt.com.mailbomb.net.mailbox.as.mailbox.co.za.mailbox.gr.mailbox.hu.mailbr.com.br.mailc.net.mailcan.com.mailchoose.co.mailcity.com.mailclub.fr.mailclub.net.mailexcite.com.mailforce.net.mailftp.com.mailgenie.net.mailhaven.com.mailhood.com.mailingweb.com.mailisent.com.mailite.com.mailme.dk.mailmight.com.mailmij.nl.mailnew.com.mailops.com.mailoye.com.mailpanda.com.mailpride.com.mailpuppy.com.mailroom.com.mailru.com.mailsent.net.mailsurf.com.mailup.net.maktoob.com.malayalamtelevision.net.manager.de.mantrafreenet.com.mantramail.com.mantraonline.com.marchmail.com.marijuana.nl.married-not.com.marsattack.com.masrawy.com.mauimail.com.maxleft.com.mbox.com.au.me-mail.hu.meetingmall.com.megago.com.megamail.pt.mehrani.com.mehtaweb.com.melodymail.com.meloo.com.message.hu.metacrawler.com.metta.lk.miesto.sk.mighty.co.za.miho-nakayama.com.millionaireintraining.com.milmail.com.misery.net.mittalweb.com.mixmail.com.ml1.net.mobilbatam.com.mohammed.com.moldova.cc.moldova.com.moldovacc.com.montevideo.com.uy.moonman.com.moose-mail.com.mortaza.com.mosaicfx.com.most-wanted.com.mostlysunny.com.motormania.com.movemail.com.mp4.it.mr-potatohead.com.mscold.com.msgbox.com.mundomail.net.munich.com.musician.org.musicscene.org.mybox.it.mycabin.com.mycity.com.mycool.com.mydomain.com.mydotcomaddress.com.myfamily.com.myiris.com.mynamedot.com.mynetaddress.com.myownemail.com.myownfriends.com.mypersonalemail.com.myplace.com.myrealbox.com.myself.com.mystupidjob.com.myway.com.myworldmail.com.n2.com.n2business.com.n2mail.com.n2software.com.nabc.biz.nagpal.net.nakedgreens.com.name.com.nameplanet.com.nandomail.com.naseej.com.nativestar.net.nativeweb.net.navigator.lv.neeva.net.nemra1.com.nenter.com.nervhq.org.net4b.pt.net4you.at.netbounce.com.netbroadcaster.com.netcenter-vn.net.netcourrier.com.netexecutive.com.netexpressway.com.netian.com.netizen.com.ar.netlane.com.netlimit.com.netmongol.com.netpiper.com.netposta.net.netralink.com.netscape.net.netscapeonline.co.uk.netspeedway.com.netsquare.com.netster.com.nettaxi.com.netzero.com.netzero.net.newmail.com.newmail.net.newmail.ru.newyork.com.nfmail.com.nicegal.com.nicholastse.net.nicolastse.com.nightmail.com.nikopage.com.nirvanafan.com.noavar.com.norika-fujiwara.com.norikomail.com.northgates.net.nospammail.net.ny.com.nyc.com.nycmail.com.nzoomail.com.o-tay.com.o2.co.uk.oceanfree.net.oddpost.com.odmail.com.oicexchange.com.okbank.com.okhuman.com.okmad.com.okmagic.com.okname.net.okuk.com.ole.com.olemail.com.olympist.net.omaninfo.com.onebox.com.onenet.com.ar.onet.pl.oninet.pt.online.ie.onlinewiz.com.onmilwaukee.com.onobox.com.operamail.com.optician.com.orbitel.bg.orgmail.net.osite.com.br.oso.com.otakumail.com.our-computer.com.our-office.com.ourbrisbane.com.ournet.md.outgun.com.over-the-rainbow.com.ownmail.net.packersfan.com.pakistanoye.com.palestinemail.com.parkjiyoon.com.parrot.com.partlycloudy.com.partynight.at.parvazi.com.pcpostal.com.pediatrician.com.penpen.com.peopleweb.com.perfectmail.com.personal.ro.personales.com.petml.com.pettypool.com.pezeshkpour.com.phayze.com.phreaker.net.picusnet.com.pigpig.net.pinoymail.com.piracha.net.pisem.net.planetaccess.com.planetout.com.plasa.com.playersodds.com.playful.com.plusmail.com.br.pmail.net.pobox.hu.pobox.sk.pochta.ru.poczta.fm.poetic.com.polbox.com.policeoffice.com.pool-sharks.com.poond.com.popmail.com.popsmail.com.popstar.com.portugalmail.com.portugalmail.pt.portugalnet.com.positive-thinking.com.post.com.post.cz.post.sk.postaccesslite.com.postafree.com.postaweb.com.postinbox.com.postino.ch.postmaster.co.uk.postpro.net.powerfan.com.praize.com.premiumservice.com.presidency.com.press.co.jp.priest.com.primposta.com.primposta.hu.pro.hu.progetplus.it.programmer.net.programozo.hu.proinbox.com.project2k.com.promessage.com.prontomail.com.psv-supporter.com.publicist.com.pulp-fiction.com.punkass.com.qatarmail.com.qprfans.com.qrio.com.quackquack.com.qudsmail.com.quepasa.com.quickwebmail.com.r-o-o-t.com.raakim.com.racingfan.com.au.radicalz.com.ragingbull.com.ranmamail.com.rastogi.net.rattle-snake.com.ravearena.com.razormail.com.rccgmail.org.realemail.net.reallyfast.biz.rediffmail.com.rediffmailpro.com.rednecks.com.redseven.de.redsfans.com.registerednurses.com.repairman.com.reply.hu.representative.com.rescueteam.com.rezai.com.rickymail.com.rin.ru.rn.com.rock.com.rocketmail.com.rodrun.com.rome.com.roughnet.com.rubyridge.com.runbox.com.rushpost.com.ruttolibero.com.s-mail.com.sabreshockey.com.sacbeemail.com.safe-mail.net.sailormoon.com.saintly.com.sale-sale-sale.com.salehi.net.samerica.com.samilan.net.sammimail.com.sanfranmail.com.sanook.com.sapo.pt.saudia.com.sayhi.net.scandalmail.com.schweiz.org.sci.fi.scientist.com.scifianime.com.scottishmail.co.uk.scubadiving.com.searchwales.com.sebil.com.secret-police.com.secretservices.net.seductive.com.seekstoyboy.com.send.hu.sendme.cz.sent.com.serga.com.ar.servemymail.com.sesmail.com.sexmagnet.com.seznam.cz.shahweb.net.shaniastuff.com.sharmaweb.com.she.com.shootmail.com.shotgun.hu.shuf.com.sialkotcity.com.sialkotian.com.sialkotoye.com.sify.com.sinamail.com.singapore.com.singmail.com.singnet.com.sg.skim.com.skizo.hu.slamdunkfan.com.slingshot.com.slo.net.slotter.com.smapxsmap.net.smileyface.comsmithemail.net.smoothmail.com.snail-mail.net.snakemail.com.sndt.net.sneakemail.com.sniper.hu.snoopymail.com.snowboarding.com.snowdonia.net.socamail.com.sociologist.com.softhome.net.sol.dk.soldier.hu.soon.com.soulfoodcookbook.com.sp.nl.space.com.spacetowns.com.spamex.com.spartapiet.com.spazmail.com.speedpost.net.spils.com.spinfinder.com.sportemail.com.spray.no.spray.se.spymac.com.srilankan.net.st-davids.net.stade.fr.stargateradio.com.starmail.com.starmail.org.starmedia.com.starplace.com.starspath.com.start.com.au.stopdropandroll.com.stribmail.com.strompost.com.strongguy.com.subram.com.sudanmail.net.suhabi.com.suisse.org.sunpoint.net.sunrise-sunset.com.sunsgame.com.sunumail.sn.superdada.com.supereva.it.supermail.ru.surf3.net.surfy.net.surimail.com.survivormail.com.sweb.cz.swiftdesk.com.swirve.com.swissinfo.org.swissmail.net.switchboardmail.com.switzerland.org.sx172.com.syom.com.syriamail.com.t2mail.com.takuyakimura.com.talk21.com.talkcity.com.tamil.com.tatanova.com.tech4peace.org.techemail.com.techie.com.technisamail.co.za.technologist.com.teenagedirtbag.com.telebot.com.teleline.es.telinco.net.telkom.net.telpage.net.tenchiclub.com.tenderkiss.com.terra.cl.terra.com.terra.com.ar.terra.com.br.terra.es.tfanus.com.er.tfz.net.thai.com.thaimail.com.thaimail.net.the-african.com.the-airforce.com.the-aliens.com.the-american.com.the-animal.com.the-army.com.the-astronaut.com.the-beauty.com.the-big-apple.com.the-biker.com.the-boss.com.the-brazilian.com.the-canadian.com.the-canuck.com.the-captain.com.the-chinese.com.the-country.com.the-cowboy.com.the-davis-home.com.the-dutchman.com.the-eagles.com.the-englishman.com.the-fastest.net.the-fool.com.the-frenchman.com.the-galaxy.net.the-genius.com.the-gentleman.com.the-german.com.the-gremlin.com.the-hooligan.com.the-italian.com.the-japanese.com.the-lair.com.the-madman.com.the-mailinglist.com.the-marine.com.the-master.com.the-mexican.com.the-ministry.com.the-monkey.com.the-newsletter.net.the-pentagon.com.the-police.com.the-prayer.com.the-professional.com.the-quickest.com.the-russian.com.the-snake.com.the-spaceman.com.the-stock-market.com.the-student.net.the-whitehouse.net.the-wild-west.com.the18th.com.thecoolguy.com.thecriminals.com.thedoghousemail.com.theend.hu.thegolfcourse.com.thegooner.com.theheadoffice.com.thelanddownunder.com.theoffice.net.thepokerface.com.thepostmaster.net.theraces.com.theracetrack.com.thestreetfighter.com.theteebox.com.thewatercooler.com.thewebpros.co.uk.thewizzard.com.thewizzkid.com.thezhangs.net.thirdage.com.thundermail.com.tidni.com.timein.net.tiscali.at.tiscali.be.tiscali.co.uk.tiscali.lu.tiscali.se.tkcity.com.topchat.com.topgamers.co.uk.topletter.com.topmail.com.ar.topsurf.com.torchmail.com.travel.li.trialbytrivia.com.trmailbox.com.tropicalstorm.com.trust-me.com.tsamail.co.za.ttml.co.in.tunisiamail.com.turkey.com.twinstarsmail.com.tycoonmail.com.typemail.com.u2club.com.uae.ac.uaemail.com.ubbi.com.ubbi.com.br.uboot.com.uk2k.com.uk2net.com.uk7.net.uk8.net.ukbuilder.com.ukcool.com.ukdreamcast.com.ukr.net.uku.co.uk.ultapulta.com.ultrapostman.com.ummah.org.umpire.com.unbounded.com.unican.es.unihome.com.universal.pt.uno.ee.uno.it.unofree.it.uol.com.ar.uol.com.br.uol.com.co.uol.com.mx.uol.com.ve.uole.com.uole.com.ve.uolmail.com.uomail.com.ureach.com.urgentmail.biz.usa.com.usanetmail.com.uymail.com.uyuyuy.com.v-sexi.com.velnet.co.uk.velocall.com.verizonmail.com.veryfast.biz.veryspeedy.net.violinmakers.co.uk.vip.gr.vipmail.ru.virgilio.it.virgin.net.virtualmail.com.visitmail.com.visto.com.vivianhsu.net.vjtimail.com.vnn.vn.volcanomail.com.vote-democrats.com.vote-hillary.com.vote-republicans.com.wahoye.com.wales2000.net.wam.co.za.wanadoo.es.warmmail.com.warpmail.net.warrior.hu.waumail.com.wearab.net.web-mail.com.ar.web-police.com.web.de.webave.com.webcity.ca.webdream.com.webindia123.com.webjump.com.webmail.co.yu.webmail.co.za.webmail.hu.webmails.com.webprogramming.com.webstation.com.websurfer.co.za.webtopmail.com.weedmail.com.weekonline.com.wehshee.com.welsh-lady.com.whartontx.com.wheelweb.com.whipmail.com.whoever.com.whoopymail.com.wildmail.com.winmail.com.au.winning.com.witty.com.wolf-web.com.wombles.com.wongfaye.com.wooow.it.workmail.com.worldemail.com.wosaddict.com.wouldilie.com.wowmail.com.wp.pl.wrexham.net.writeme.com.writemeback.com.wrongmail.com.www.com.wx88.net.wxs.net.x-mail.net.x5g.com.xmsg.com.xoom.com.xsmail.com.xuno.com.xzapmail.com.yada-yada.com.yaho.com.yahoo.ca.yahoo.co.in.yahoo.co.jp.yahoo.co.kr.yahoo.co.nz.yahoo.co.uk.yahoo.com.yahoo.com.ar.yahoo.com.au.yahoo.com.br.yahoo.com.cn.yahoo.com.hk.yahoo.com.is.yahoo.com.mx.yahoo.com.ru.yahoo.com.sg.yahoo.de.yahoo.dk.yahoo.es.yahoo.fr.yahoo.ie.yahoo.it.yahoo.jp.yahoo.ru.yahoo.se.yahoofs.com.yalla.com.yalla.com.lb.yalook.com.yam.com.yandex.ru.yapost.com.yebox.com.yehey.com.yemenmail.com.yepmail.net.yifan.net.yopolis.com.youareadork.com.your-house.com.yourinbox.com.yourlover.net.yournightmare.com.yours.com.yourssincerely.com.yourteacher.net.yourwap.com.yuuhuu.net.yyhmail.com.zahadum.com.zeepost.nl.zhaowei.net.zip.net.zipido.com.ziplip.com.zipmail.com.zipmail.com.br.zipmax.com.zmail.ru.zonnet.nl.zubee.com.zuvio.com.zwallet.com.zybermail.com.zzn.com.zzom.co.uk.111mail.com.123iran.com.1-usa.com.2die4.com.37.com.420email.com.4degreez.com.4-music-today.com.5.am.5005.lv.8.am.a.ua.a.org.ua.abha.cc.accountant.com.actingbiz.com.adexec.com.africamail.com.agadir.cc.ahsa.ws.ajman.cc.ajman.us.ajman.ws.albaha.cc.alex4all.com.alexandria.cc.algerie.cc.allergist.com.allhiphop.com.alriyadh.cc.alumnidirector.com.amman.cc.anatomicrock.com.animeone.com.anjungcafe.com.aqaba.cc.aol.com.arar.ws.archaeologist.com.arcticmail.com.artlover.com.asia.com.asiancutes.com.aswan.cc.a-teens.net.ausi.com.australiamail.com.autoindia.com.autopm.com.baalbeck.cc.bahraini.cc.banha.cc.barriolife.com.b-boy.com.beautifulboy.com.berlin.com.bgay.com.bicycledata.com.bicycling.com.bigheavyworld.com.bigmailbox.net.bikerheaven.net.bikerider.com.bikermail.com.billssite.com.bizerte.cc.bk.ru.blackandchristian.com.blackcity.net.blackvault.com.blida.info.bmx.lv.bmxtrix.com.boarderzone.com.boatnerd.com.bolbox.com.bongmail.com.bowl.com.box.az.buraydah.cc.butch-femme.org.byke.com.calle22.com.cameroon.cc.cannabismail.com.catlover.com.catlovers.com.certifiedbitches.com.championboxing.com.chatway.com.cheerful.com.chemist.com.chillymail.com.classprod.com.classycouples.com.clerk.com.cliffhanger.com.columnist.com.comic.com.company.org.ua.congiu.net.consultant.com.coolmail.ru.coolshit.com.corpusmail.com.counsellor.com.cutey.com.cyberunlimited.org.cycledata.com.darkfear.com.darkforces.com.deliveryman.com.dhahran.cc.dhofar.cc.dino.lv.diplomats.com.dirtythird.com.djibouti.cc.doctor.com.doglover.com.dominican.cc.dopefiends.com.dr.com.draac.com.drakmail.net.dr-dre.com.dreamstop.com.dublin.com.earthling.net.earthling.net.eclub.lv.egypt.net.e-mail.am.email.com.e-mail.ru.emailfast.com.emails.ru.e-mails.ru.eminemfans.com.envirocitizen.com.eritrea.cc.eritrea.cc.escapeartist.com.europe.com.execs.com.ezsweeps.com.falasteen.cc.famous.as.farts.com.feelingnaughty.com.financier.com.firemyst.com.fit.lv.freeonline.com.fromru.com.front.ru.fudge.com.fujairah.cc.fujairah.us.fujairah.ws.funkytimes.com.gabes.cc.gafsa.cc.gala.net.gamerssolution.com.gardener.com.gawab.com.gazabo.net.geologist.com.giza.cc.glittergrrrls.com.gmail.com.goatrance.com.goddess.com.gohip.com.goldenmail.ru.goldmail.ru.gospelcity.com.gothicgirl.com.gotomy.com.grapemail.net.graphic-designer.com.greatautos.org.guinea.cc.guinea.cc.guy.com.hacker.am.hairdresser.net.haitisurf.com.hamra.cc.happyhippo.com.hasakah.com.hateinthebox.com.hebron.tv.hiphopmail.com.homs.cc.hotbox.ru.hotmail.com.hotmail.ru.hot-shot.com.houseofhorrors.com.hugkiss.com.hullnumber.com.human.lv.ibra.cc.idunno4recipes.com.ihatenetscape.com.iname.com.inbox.ru.inorbit.com.insurer.com.intimatefire.com.iphon.biz.irbid.ws.irow.com.ismailia.cc.jadida.cc.jadida.org.japan.com.jazzemail.com.jerash.cc.jizan.cc.jouf.cc.journalist.com.juanitabynum.com.kairouan.cc.kanoodle.com.karak.cc.khaimah.cc.khartoum.cc.khobar.cc.kickboxing.com.kidrock.com.kinkyemail.com.kool-things.com.krovatka.net.kuwaiti.tv.kyrgyzstan.cc.land.ru.latakia.cc.latchess.com.latinabarbie.com.latinogreeks.com.lawyer.com.lebanese.cc.leesville.com.legislator.com.list.ru.lobbyist.com.london.com.loveable.com.loveemail.com.loveis.lv.lovers-mail.com.lowrider.com.lubnan.cc.lubnan.ws.lucky7lotto.net.lv-inter.net.mad.scientist.com.madeniggaz.net.madinah.cc.madrid.com.maghreb.cc.mail.com.mail.ru.mail15.com.mail333.com.mailbomb.com.manama.cc.mansoura.tv.marillion.net.marrakesh.cc.mascara.ws.megarave.com.meknes.cc.mesra.net.mindless.com.minister.com.mofa.com.moscowmail.com.motley.com.munich.com.muscat.tv.muscat.ws.music.com.musician.net.musician.org.musicsites.com.myself.com.mymail-in.net.mytop-in.net.nabeul.cc.nabeul.info.nablus.cc.nador.cc.najaf.cc.narod.ru.netbroadcaster.com.netfingers.com.net-surf.com.nettaxi.com.newmail.ru.ni.cedriveway.com.nightmail.ru.nm.ru.nocharge.com.nycmail.com.omani.ws.omdurman.cc.operationivy.com.optician.com.oran.cc.oued.info.oued.org.oujda.biz.oujda.cc.paidoffers.net.pakistani.ws.palmyra.cc.palmyra.ws.pcbee.com.pediatrician.com.persian.com.petrofind.com.peugeot-club.org.phunkybitches.com.pikaguam.com.pinkcity.net.pisem.net.pitbullmail.com.planetsmeg.com.playful.com.pochta.ru.pochtamt.ru.poetic.com.pookmail.com.poop.com.poormail.com.pop3.ru.popstar.com.portsaid.cc.post.com.potsmokersnet.com.presidency.com.priest.com.primetap.com.programmer.net.project420.com.prolife.net.publicist.com.puertoricowow.com.puppetweb.com.qassem.cc.quds.cc.rabat.cc.rafah.cc.ramallah.cc.rambler.ru.rapstar.com.rapworld.com.rastamall.com.ratedx.net.ravermail.com.rbcmail.ru.realtyagent.com.registerednurses.com.relapsecult.com.remixer.com.repairman.com.representative.com.rescueteam.com.rockeros.com.romance106fm.com.rome.com.saveourplanet.org.safat.biz.safat.info.safat.us.safat.ws.saintly.com.salalah.cc.salmiya.biz.samerica.com.sanaa.cc.sanfranmail.com.scientist.com.seductive.com.seeb.cc.sexriga.lv.sfax.ws.sharm.cc.sinai.cc.singalongcenter.com.singapore.com.siria.cc.sketchyfriends.com.slayerized.com.smartstocks.com.smtp.ru.sociologist.com.sok.lv.soon.com.soulja-beatz.org.sousse.cc.spam.lv.specialoperations.com.speedymail.net.spells.com.streetracing.com.subspacemail.com.sudanese.cc.suez.cc.sugarray.com.superbikeclub.com.superintendents.net.supermail.ru.surfguiden.com.sweetwishes.com.tabouk.cc.tajikistan.cc.tangiers.cc.tanta.cc.tattoodesign.com.tayef.cc.teamster.net.techie.com.technologist.com.teenchatnow.com.tetouan.cc.the5thquarter.com.theblackmarket.com.timor.cc.tokyo.com.tombstone.ws.topping.com.ua.troamail.org.tunisian.cc.tunisian.cc.tut.by.tx.am.u2tours.com.ua.fm.uaix.info.umpire.com.urdun.cc.usa.com.vipmail.ru.vitalogy.org.whatisthis.com.whoever.com.winning.com.witty.com.woman.in.ua.wrestlezone.com.writeme.com.writeme.com.xsecurity.org.yahoo.com.yanbo.cc.yandex.ru.yemeni.cc.yemeni.cc.yogaelements.com.yours.com.yunus.cc.zabor.lv.zagazig.cc.zambia.cc.zarqa.cc.zerogravityclub.com.museumfan.com.showfans.com.docemail.com.healthemail.net.topnurses.net.teachfitness.com.carjunky.com.fasthondas.com.fastestcar.com.v8email.com.aviationemail.com.bankersmail.com.chefmail.com.insurancefan.com.legalfan.com.policeone.com.siliconemail.com.techemail.com.theguilds.org.homeworking.org.officeemail.net.tnbusiness.com.aemail4u.com.blazemail.com.buildtraffic.com.thedoghousemail.com.thefreesite.com.gobot.com.hotsheet.com.internetfan.com.mail.vu.mailbling.com.muchomail.com.netzoola.com.spinfinder.com.ultimateemail.com.wowmail.com.computermail.net.createmail.com.eliteral.com.hardwarefan.com.softwarefan.com.softwareaddict.com.sqatester.com.ecologyfund.com.happyhippie.com.nativeweb.org.stylefan.com.bornagain.com.catholicemail.com.khalsa.com.collegestudentmail.com.highschoolemail.com.math.com.teachingzone.net.coolyork.com.dcemail.com.floridaemail.net.islandfan.com.mauiemail.com.cbgb.com.darksites.com.herzeleid.com.k-earthmail.com.knac.com.jenniferlopezfan.com.mugglenet.com.radio.fm.riversongs.net.tranceaddict.com.sluggy.net.survivoremail.net.vampirefreaks.com.aggressive.com.baseballexpert.net.bodybuilders.com.footballaddict.net.lovetohike.com.surf.co.nz.triathlete.com.volleyballmail.com.animalfan.net.cookingfan.net.horsemail.com.meowmail.com.parentsof2.com.genxemail.com.opendiary.com.ravefan.com.youthfire.com.financefan.net.investingfan.com.stocksplayers.com.bust.com.ladyfire.com.modernwife.com.womensmail.net.cheatcc.com.chessfan.net.mahjongplayer.com.aussiemail.com.bakililar.az.canadiansrule.com.email.co.yu.jamaicans.com.humlog.com.postamatik.com.parisfan.net.gmail.ru.dinamomail.ru.hrono.ru.icqfoto.ru.kievmail.ru.lmail.ru.mirabilis.ru.radiomail.ru.refer.ru.spartakmail.ru.tvmail.ru.wmail.ru.womenmail.ru.elitemail.ru.inet.ua.fm.com.ua.freemail.com.ua.bigmir.net.itua.info.meta.ua.1ru.net.barnaul.biz.barnaul.info.belgorod.tv.biysk.biz.chernogolovka.com.ivanovo.biz.kaluga.biz.kaluga.tv.kazan.biz.kemerovo.tv.kirov.info.kirov.tv.kolyma.org.kostroma.biz.kostroma.tv.kursk.biz.lipetsk.biz.lipetsk.info.lipetsk.tv.magadan.info.magadan.tv.maradan.biz.mari-el.biz.murmansk.biz.murmansk.tv.norilsk.biz.norilsk.info.norilsk.tv.nsk.biz.obninsk.biz.obninsk.info.obninsk.net.obninsk.tv.obrazovanie.com.obrazovanie.net.obrazovanie.org.omsk.biz.orel.tv.orenburg.tv.penza.biz.penza.info.penza.tv.pskov.biz.pskov.tv.rostov.tv.runet.biz.samara.biz.saratov.tv.smolensk.biz.smolensk.info.smolensk.tv.stavropol.biz.stavropol.tv.surgut.biz.surgut.tv.svetlogorsk.biz.tambov.tv.tomsk.biz.tomsk.info.tomsk.tv.tula.biz.tula.info.tumen.biz.tver.biz.tver.info.tver.tv.ufa.biz.upakovka.com.upakovka.org.volga.tv.yamal.biz.yamal.info.hotpop.com.punkass.com.sexmagnet.com.bonbon.net.toughguy.net.phreaker.net.gamebox.net';
            // jscs:enable maximumLineLength
            var domain = value.replace(/.*@/, '');
            return publicDomains.indexOf(domain) < 0;
        },
        alpha: /^[a-zA-Z]+$/,
        number: /^[-+]?\d*(?:[\.\,]\d+)?$/,
        //email: /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$/
    };

    WG.addComponent(Validate);
})();

(function () {
    'use strict';

    /**
     * Class constructor for Layout MDL component.
     * Implements MDL component design pattern defined at:
     * https://github.com/jasonmayes/mdl-component-design-pattern
     *
     * @constructor
     * @param {HTMLElement} element The element that will be upgraded.
     */
    var WGFooter = function WGFooter(element) {
        this.element_ = element;

        // Initialize instance.
        this.init();
    };
    window.WGFooter = WGFooter;

    /**
     * Store constants in one place so they can be updated easily.
     *
     * @enum {string | number}
     * @private
     */
    WGFooter.prototype.Constant_ = {
        MAX_WIDTH: '(max-width: 1024px)',
        TAB_SCROLL_PIXELS: 100,

        MENU_ICON: 'menu',
        CHEVRON_LEFT: 'chevron_left',
        CHEVRON_RIGHT: 'chevron_right'
    };

    /**
     * Store strings for class names defined by this component that are used in
     * JavaScript. This allows us to simply change it in one place should we
     * decide to modify at a later date.
     *
     * @enum {string}
     * @private
     */
    WGFooter.prototype.CssClasses_ = {
        CONTAINER: 'wg-footer__container',
        HEADER: 'wg-footer__header',
        DRAWER: 'wg-footer__drawer',
        CONTENT: 'wg-footer__content',
        DRAWER_BTN: 'wg-footer__drawer-button',

        HEADER_SEAMED: 'wg-footer__header--seamed',
        HEADER_WATERFALL: 'wg-footer__header--waterfall',
        HEADER_SCROLL: 'wg-footer__header--scroll',

        FIXED_HEADER: 'mdl-layout--fixed-header',
        OBFUSCATOR: 'wg-footer__obfuscator',

        PANEL: 'wg-footer__tab-panel',

        CASTING_SHADOW: 'is-casting-shadow',
        IS_COMPACT: 'is-compact',
        IS_SMALL_SCREEN: 'is-small-screen',
        IS_DRAWER_OPEN: 'is-visible',
        IS_ACTIVE: 'is-active',
        IS_UPGRADED: 'is-upgraded',
        IS_ANIMATING: 'is-animating',

        ON_LARGE_SCREEN: 'mdl-layout--large-screen-only',
        ON_SMALL_SCREEN: 'mdl-layout--small-screen-only'
    };

    /**
     * Handles scrolling on the content.
     *
     * @private
     */
    WGFooter.prototype.contentScrollHandler_ = function () {
        if (this.header_.classList.contains(this.CssClasses_.IS_ANIMATING)) {
            return;
        }

        this.header_.classList.add(this.CssClasses_.CASTING_SHADOW);
        this.header_.classList.add(this.CssClasses_.IS_COMPACT);
        this.header_.classList.add(this.CssClasses_.IS_ANIMATING);
    };

    /**
     * Handles changes in screen size.
     *
     * @private
     */
    WGFooter.prototype.screenSizeHandler_ = function () {
        if (this.screenSizeMediaQuery_.matches) {
            this.element_.classList.add(this.CssClasses_.IS_SMALL_SCREEN);
        } else {
            this.element_.classList.remove(this.CssClasses_.IS_SMALL_SCREEN);
            // Collapse drawer (if any) when moving to a large screen size.
            if (this.drawer_) {
                this.drawer_.classList.remove(this.CssClasses_.IS_DRAWER_OPEN);
                this.obfuscator_.classList.remove(this.CssClasses_.IS_DRAWER_OPEN);
            }
        }
    };

    /**
     * Handles (un)setting the `is-animating` class
     *
     * @private
     */
    WGFooter.prototype.headerTransitionEndHandler_ = function () {
        this.header_.classList.remove(this.CssClasses_.IS_ANIMATING);
    };

    /**
     * Initialize element.
     */
    WGFooter.prototype.init = function () {
        if (this.element_) {
            var container = document.createElement('div');
            container.classList.add(this.CssClasses_.CONTAINER);
            this.element_.parentElement.insertBefore(container, this.element_);
            this.element_.parentElement.removeChild(this.element_);
            container.appendChild(this.element_);

            // Keep an eye on screen size, and add/remove auxiliary class for styling
            // of small screens.
            this.screenSizeMediaQuery_ = window.matchMedia(
                /** @type {string} */ (this.Constant_.MAX_WIDTH));
            this.screenSizeMediaQuery_.addListener(this.screenSizeHandler_.bind(this));
            this.screenSizeHandler_();

            this.element_.classList.add(this.CssClasses_.IS_UPGRADED);

            $(function () {
                var langSelectorElement = $('.wg-footer__lang-selector-wrapper'),
                    langSelectorTrigger = $('.wg-footer__lang-selector-trigger'),
                    langSelectorItem = $('.wg-footer__lang-selector-item');

                function setLang(value, text) {
                    langSelectorTrigger.find('.wg-footer__lang-selector-trigger').text(text);
                    langSelectorItem
                    .removeAttr('data-selected')
                    .filter('[data-value="' + value + '"]')
                    .attr('data-selected', '');
                }

                langSelectorTrigger.on('click', function (e) {
                    e.stopPropagation();
                    langSelectorElement.toggleClass('opened');
                });
                langSelectorItem.on('click', function () {
                    setLang($(this).data('value'), $(this).text());
                    langSelectorElement.removeClass('opened');
                });

                $('.wg-footer, .site').on('click', function () {
                    langSelectorElement.removeClass('opened');
                });

                var headings = $('.wg-footer__heading');
                var spoilerHeadings = headings.filter('.wg-footer__heading--spoiler');
                spoilerHeadings.on('click', function () {
                    $(this).toggleClass('wg-footer__heading--spoiler-opened');
                });

            });
        }
    };

    componentHandler.register({
        constructor: WGFooter,
        classAsString: 'WGFooter',
        cssClass: 'wg-footer'
    });
})();


(function () {
    'use strict';

    /**
     * @constructor
     * @param {HTMLElement} element The element that will be upgraded.
     */
    var WGHeader = function WGHeader(element) {
        this.element_ = element;

        this.isMobile = false;

        this.init();
        this.screenSizeHandler_();
    };
    window.WGHeader = WGHeader;

    /**
     * Return jQuery objects
     */
    WGHeader.prototype.$self = function () {
        return $(this.element_);
    };
    WGHeader.prototype.find = function (selectorPart) {
        return this.$self().find('.' + this.CssClasses_.BASE + '__' + selectorPart);
    };
    WGHeader.prototype.$burger = function () {
        return this.find('mobile-burger');
    };
    WGHeader.prototype.$overlay = function () {
        return this.find('mobile-overlay');
    };
    WGHeader.prototype.$overlayNav = function () {
        return this.find('mobile-overlay-nav');
    };
    WGHeader.prototype.$topMenu = function () {
        return this.find('top-menu');
    };
    WGHeader.prototype.$stickySubMenu = function () {
        return this.find('sticky-menu-sub');
    };
    WGHeader.prototype.$warningDesktop = function () {
        return this.find('warning-desktop');
    };

    /**
     * Store constants in one place so they can be updated easily.
     *
     * @enum {string | number}
     * @private
     */
    WGHeader.prototype.Constant_ = {
        MOBILE_VIEWPORT_MEDIA: '(max-width: 1023px)',
        TAB_SCROLL_PIXELS: 100,

        MENU_ICON: 'menu',
        CHEVRON_LEFT: 'chevron_left',
        CHEVRON_RIGHT: 'chevron_right'
    };

    /**
     * Store strings for class names defined by this component that are used in
     * JavaScript. This allows us to simply change it in one place should we
     * decide to modify at a later date.
     *
     * @enum {string}
     * @private
     */
    WGHeader.prototype.CssClasses_ = {
        BASE: 'wg-header',
        WARNING_OPENED: 'wg-header--warning-opened',
        PROMO_OPENED: 'wg-header--promo-opened',

        OVERLAY_OPENED: 'wg-header--overlay-opened',
        OVERLAY_OPENING: 'wg-header--overlay-opening',
        OVERLAY_CLOSING: 'wg-header--overlay-closing',

        STICKY_SUBMENU_OPENED: 'wg-header--sub-menu-opened',
        STICKY_SUBMENU_OPENING: 'wg-header--sub-menu-opening',
        STICKY_SUBMENU_CLOSING: 'wg-header--sub-menu-closing',

        CONTAINER: 'wg-header__container',
        HEADER: 'wg-header__header',
        DRAWER: 'wg-header__drawer',
        CONTENT: 'wg-header__content',
        DRAWER_BTN: 'wg-header__drawer-button',

        ICON: 'material-icons',

        JS_RIPPLE_EFFECT: 'mdl-js-ripple-effect',
        RIPPLE_CONTAINER: 'wg-header__tab-ripple-container',
        RIPPLE: 'mdl-ripple',
        RIPPLE_IGNORE_EVENTS: 'mdl-js-ripple-effect--ignore-events',

        HEADER_SEAMED: 'wg-header__header--seamed',
        HEADER_WATERFALL: 'wg-header__header--waterfall',
        HEADER_SCROLL: 'wg-header__header--scroll',

        FIXED_HEADER: 'mdl-layout--fixed-header',
        OBFUSCATOR: 'wg-header__obfuscator',

        TAB_BAR: 'wg-header__tab-bar',
        TAB_CONTAINER: 'wg-header__tab-bar-container',
        TAB: 'wg-header__tab',
        TAB_BAR_BUTTON: 'wg-header__tab-bar-button',
        TAB_BAR_LEFT_BUTTON: 'wg-header__tab-bar-left-button',
        TAB_BAR_RIGHT_BUTTON: 'wg-header__tab-bar-right-button',
        PANEL: 'wg-header__tab-panel',

        HAS_DRAWER: 'has-drawer',
        HAS_TABS: 'has-tabs',
        HAS_SCROLLING_HEADER: 'has-scrolling-header',
        CASTING_SHADOW: 'is-casting-shadow',
        IS_COMPACT: 'is-compact',
        IS_SMALL_SCREEN: 'is-small-screen',
        IS_DRAWER_OPEN: 'is-visible',
        IS_ACTIVE: 'is-active',
        IS_UPGRADED: 'is-upgraded',
        IS_ANIMATING: 'is-animating',

        ON_LARGE_SCREEN: 'mdl-layout--large-screen-only',
        ON_SMALL_SCREEN: 'mdl-layout--small-screen-only'
    };

    /**
     * Handles scrolling on the content.
     *
     * @private
     */
    WGHeader.prototype.contentScrollHandler_ = function () {
        if (this.header_.classList.contains(this.CssClasses_.IS_ANIMATING)) {
            return;
        }

        this.header_.classList.add(this.CssClasses_.CASTING_SHADOW);
        this.header_.classList.add(this.CssClasses_.IS_COMPACT);
        this.header_.classList.add(this.CssClasses_.IS_ANIMATING);
    };

    /**
     * Handles changes in screen size.
     *
     * @private
     */
    WGHeader.prototype.screenSizeHandler_ = function () {
        if (this.screenSizeMediaQuery_.matches) {
            this.element_.classList.add(this.CssClasses_.IS_SMALL_SCREEN);
            this.isMobile = true;
        } else {
            this.element_.classList.remove(this.CssClasses_.IS_SMALL_SCREEN);
            this.isMobile = false;
        }
    };

    /**
     * Handles (un)setting the `is-animating` class
     *
     * @private
     */
    WGHeader.prototype.headerTransitionEndHandler_ = function () {
        this.header_.classList.remove(this.CssClasses_.IS_ANIMATING);
    };

    WGHeader.prototype.openOverlay = function () {
        this.$self().addClass(this.CssClasses_.OVERLAY_OPENING);
        this.$self().addClass(this.CssClasses_.OVERLAY_OPENED);
        $('body').addClass('is-overlay-menu-opened');
    };

    WGHeader.prototype.closeOverlay = function () {
        this.$self().removeClass(this.CssClasses_.OVERLAY_OPENED);
        this.$self().addClass(this.CssClasses_.OVERLAY_CLOSING);
        $('body').removeClass('is-overlay-menu-opened');
        this.closeSubmenu();
    };

    WGHeader.prototype.openSubmenu = function () {
        this.$self().addClass(this.CssClasses_.STICKY_SUBMENU_OPENED);
        this.$self().addClass(this.CssClasses_.STICKY_SUBMENU_OPENING);
    };

    WGHeader.prototype.closeSubmenu = function () {
        this.$self().removeClass(this.CssClasses_.STICKY_SUBMENU_OPENED);
        this.$self().removeClass(this.CssClasses_.STICKY_SUBMENU_OPENING);
    };

    WGHeader.prototype.init = function () {
        var self = this;

        // if (WG.utils.getCookie('accept-policy') === undefined) {
        //     self.$self().addClass(self.CssClasses_.WARNING_OPENED);
        //     $('body').addClass('cookies-warning');
        // }

        if (WG.utils.getCookie('promo-closed') === undefined  &&
            'GlobalTradenet' in window && 'headerPromoText' in window.GlobalTradenet) {
            self.$self().addClass(self.CssClasses_.PROMO_OPENED);
            $('body').addClass('promo-banner');
        }

        if (self.element_) {
            var container = document.createElement('div');
            container.classList.add(self.CssClasses_.CONTAINER);
            self.element_.parentElement.insertBefore(container, self.element_);
            self.element_.parentElement.removeChild(self.element_);
            container.appendChild(self.element_);

            // Keep an eye on screen size, and add/remove auxiliary class for styling
            // of small screens.
            self.screenSizeMediaQuery_ = window.matchMedia(
                (self.Constant_.MOBILE_VIEWPORT_MEDIA)
            );
            self.screenSizeMediaQuery_.addListener(self.screenSizeHandler_.bind(self));
            self.screenSizeHandler_();

            self.element_.classList.add(self.CssClasses_.IS_UPGRADED);
        }

        self.$burger().on('click', function () {
            if (self.$self().hasClass(self.CssClasses_.OVERLAY_OPENED)) {
                self.closeOverlay();
            } else {
                self.openOverlay();
            }
        });

        self.$overlay().on('transitionend', function () {
            if (self.$self().hasClass(self.CssClasses_.OVERLAY_OPENED)) {
                self.$self().removeClass(self.CssClasses_.OVERLAY_OPENING);
            } else {
                self.$self().removeClass(self.CssClasses_.OVERLAY_CLOSING);
            }
        });

        self.find('desktop-primary-nav-item--parent')
            .add(self.find('mobile-overlay-menu-primary-item--parent'))
            .on('click', function () {
                if (self.$self().hasClass(self.CssClasses_.STICKY_SUBMENU_OPENED)) {
                    self.closeSubmenu();
                } else {
                    self.openSubmenu();
                }
            });

        self.$self().on('transitionend', function () {
            if (self.$self().hasClass(self.CssClasses_.STICKY_SUBMENU_OPENED)) {
                self.$self().removeClass(self.CssClasses_.STICKY_SUBMENU_OPENING);
            }
        });

        self.find('free-trial-input')
            .on('focus', function () {
                self.$self().addClass(self.CssClasses_.BASE + '--focused');
            })
            .on('blur', function () {
                self.$self().removeClass(self.CssClasses_.BASE + '--focused');
            });

        self.fixedState();

        $(window).resize(function () {
            self.fixedState();
            if (!self.isMobile) {
                self.closeOverlay();
                self.$self().removeClass(self.CssClasses_.OVERLAY_OPENED);
                self.$self().removeClass(self.CssClasses_.OVERLAY_OPENING);
                self.$self().removeClass(self.CssClasses_.OVERLAY_CLOSING);
            }
        });
        $(window).scroll(function () {
            self.fixedState();
            self.closeSubmenu();
        });

        self.$overlay().on('click', function () {
            self.closeOverlay();
        });
        self.$overlayNav().on('click', function (e) {
            e.stopPropagation();
        });
        // if ('GlobalTradenet' in window && 'headerAnnouncement' in window.GlobalTradenet) {
        //     var announcement = window.GlobalTradenet.headerAnnouncement;
        //     announcement = announcement.split('<br>');
        //     self.find('announcement').html('');
        //     for (var i in announcement) {
        //         self.find('announcement').append(announcement[i]);
        //     }
        // }
        //
        // if (WG.utils.getCookie('accept-policy') === undefined &&
        //     'GlobalTradenet' in window &&
        //     'headerCookiesWarning' in window.GlobalTradenet) {
        //
        //     // Insert mobile warning
        //     self.find('mobile').prepend(
        //         '<div class="wg-header__warning-mobile">' +
        //             '<div class="wg-header__warning-mobile-content">' +
        //                 '<a href="/privacy/" class="wg-header__warning-content"></a>' +
        //             '</div>' +
        //             '<span class="' +
        //             'wg-header__warning-close wg-header__warning-mobile-close' +
        //             '" role="button" title="Close">' +
        //                 '<i class="wg-header__warning-mobile-close-icon"></i>' +
        //             '</span>' +
        //         '</div>'
        //     );
        //
        //     // Insert desktop warning
        //     self.$topMenu().after(
        //         '<div class="wg-header__warning wg-header__warning-desktop">' +
        //             '<div class="wg-grid">' +
        //             '<div class="wg-cell">' +
        //             '<div class="wg-header__warning-desktop-content">' +
        //             '<a href="/privacy/" class="wg-header__warning-content"></a>' +
        //             '<span class="' +
        //             'wg-header__warning-close wg-header__warning-desktop-close' +
        //             '" role="button" title="Close">' +
        //                 '<i class="wg-header__warning-desktop-close-icon"></i>' +
        //             '</span>' +
        //             '</div>' +
        //             '</div>' +
        //             '</div>' +
        //         '</div>'
        //     );
        //
        //     // insert text preventing line breaks
        //     var text = window.GlobalTradenet.headerCookiesWarning;
        //     text = text.split('<br>');
        //     self.find('warning-content').html('');
        //     for (var j in text) {
        //         self.find('warning-content').append(text[j]);
        //         self.find('warning-content').append('<br>');
        //     }
        // }
        // $(document).on('click', '.' + self.CssClasses_.BASE + '__warning-close', function () {
        //     WG.utils.setCookie('accept-policy', true, {
        //         path: '/',
        //         expires: 31536000
        //     });
        //     self.$self().removeClass('wg-header--warning-opened');
        //     $('body').removeClass('cookies-warning');
        // });

        if (WG.utils.getCookie('promo-closed') === undefined &&
            'GlobalTradenet' in window &&
            'headerPromoText' in window.GlobalTradenet) {

            self.find('mobile').prepend(
                '<div class="wg-header__promo-mobile">' +
                    '<div class="wg-header__promo-mobile-content">' +
                        '<a href="/privacy/" class="wg-header__promo-content"></a>' +
                    '</div>' +
                    '<span class="' +
                    'wg-header__promo-close wg-header__promo-mobile-close' +
                    '" role="button" title="Close">' +
                        '<i class="wg-header__promo-mobile-close-icon"></i>' +
                    '</span>' +
                '</div>'
            );

            self.$topMenu().after(
                '<div class="wg-header__promo wg-header__promo-desktop">' +
                    '<div class="wg-grid">' +
                    '<div class="wg-cell">' +
                    '<div class="wg-header__promo-desktop-content">' +
                    '<span class="wg-header__promo-content"></span>' +
                    '<span class="' +
                    'wg-header__promo-close wg-header__promo-desktop-close' +
                    '" role="button" title="Close">' +
                        '<i class="wg-header__promo-desktop-close-icon"></i>' +
                    '</span>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                '</div>'
            );

            self.find('promo-content').html(window.GlobalTradenet.headerPromoText);
        }
        $(document).on('click', '.' + self.CssClasses_.BASE + '__promo-close', function () {
            WG.utils.setCookie('promo-closed', true, {
                path: '/',
                expires: 31536000
            });
            self.$self().removeClass('wg-header--promo-opened');
            $('body').removeClass('promo-banner');
        });
    };

    /**
     * Do something if page scrolled
     */
    WGHeader.prototype.fixedState = function () {
        var self = this;
        var warningHeight = self.$warningDesktop().is(':visible') ? self.$warningDesktop().height() : 0;
        var fixPosition = this.isMobile ? 0 : self.$topMenu().height() + warningHeight;

        if (window.pageYOffset > fixPosition) {
            self.$self().addClass(this.CssClasses_.BASE + '--fixed');
        } else {
            self.$self().removeClass(this.CssClasses_.BASE + '--fixed');
        }

        if (self.find('free-trial-input').is(':focus')) {
            self.$self().addClass(this.CssClasses_.BASE + '--focused');
        } else {
            self.$self().removeClass(this.CssClasses_.BASE + '--focused');
        }
    };

    // The component registers itself. It can assume componentHandler is available
    // in the global scope.
    componentHandler.register({
        constructor: WGHeader,
        classAsString: 'WGHeader',
        cssClass: 'wg-header'
    });

})();

(function () {
    'use strict';

    var WGInput = function WGInput(element) {
        this.element_ = element;
        this.init();
    };
    window.WGInput = WGInput;

    // WGInput.prototype.Constant_ = {
    //
    // };

    WGInput.prototype.CssClasses_ = {
        IS_FILLED: 'wg-textfield__input--filled'
    };

    WGInput.prototype.init = function () {
        var self = this;
        var $this = $(this.element_);
        if (this.element_) {
            $(function () {

                $this.on('focusout', function () {
                    if ($this.val() === '') {
                        $this.removeClass(self.CssClasses_.IS_FILLED);
                    } else {
                        $this.addClass(self.CssClasses_.IS_FILLED);
                    }
                });

            });
        }
    };

    componentHandler.register({
        constructor: WGInput,
        classAsString: 'WGInput',
        cssClass: 'wg-input'
    });
})();


(function () {
    'use strict';

    var WGSelect = function WGSelect(element) {
        this.element_ = element;
        this.init();
    };

    window.WGSelect = WGSelect;

    WGSelect.prototype.CssClasses_ = {
        IS_FILLED: 'wg-textfield__select--filled'
    };

    WGSelect.prototype.init = function () {
        var self = this;
        var $this = $(this.element_);
        if (this.element_) {
            $(function () {

                $this.on('change', function () {
                    if ($this.val() === '') {
                        $this.removeClass(self.CssClasses_.IS_FILLED);
                    } else {
                        $this.addClass(self.CssClasses_.IS_FILLED);
                    }
                });

            });
        }
    };

    componentHandler.register({
        constructor: WGSelect,
        classAsString: 'WGSelect',
        cssClass: 'wg-select'
    });
})();
