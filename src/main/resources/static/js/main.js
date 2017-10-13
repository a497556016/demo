'use strict';

/* Controllers */

angular.module('app')
  .controller('AppCtrl', ['$scope', '$translate', '$localStorage', '$window', 
    function(              $scope,   $translate,   $localStorage,   $window ) {
      // add 'ie' classes to html
      var isIE = !!navigator.userAgent.match(/MSIE/i);
      isIE && angular.element($window.document.body).addClass('ie');
      isSmartDevice( $window ) && angular.element($window.document.body).addClass('smart');

      // config
      $scope.app = {
        name: '管理模板',
        version: '1.3.3',
        // for chart colors
        color: {
          primary: '#7266ba',
          info:    '#23b7e5',
          success: '#27c24c',
          warning: '#fad733',
          danger:  '#f05050',
          light:   '#e8eff0',
          dark:    '#3a3f51',
          black:   '#1c2b36'
        },
        settings: {
          themeID: 1,
          navbarHeaderColor: 'bg-black',
          navbarCollapseColor: 'bg-white-only',
          asideColor: 'bg-black',
          headerFixed: true,
          asideFixed: false,
          asideFolded: false,
          asideDock: false,
          container: false
        },
        //菜单数据
        menuData : [{
        	header : '系统设置',
        	menus : [{
        		id : '100',
        		text : '系统管理',
        		sref : 'app.sys',
        		href : '/sys',
        		children : [{
        			id : '100001',
        			text : '用户管理',
        			href : '/userMana',
        			sref : 'app.sys.userMana',
        			tempUrl : 'tpl/sys/userMana.html',
        			ctrl : 'js/controllers/sys/userManaCtl.js'
        		},{
        			id : '100002',
        			text : '角色管理',
        			href : '/role',
        			sref : 'app.sys.roleMana',
        			tempUrl : 'tpl/sys/roleMana.html',
        			ctrl : 'js/controllers/sys/roleManaCtl.js'
        		}]
        	},{
        		id : '200',
        		text : '流程管理',
        		sref : 'app.flow',
        		href : '/flow',
        		children : [{
        			id : '200001',
        			text : '实例管理',
        			href : '/flowInst',
        			sref : 'app.flow.flowInst',
        			tempUrl : 'tpl/user/flowInst.html',
        			ctrl : 'js/controllers/user/flowInstCtl.js'
        		}]
        	}]
        },{
        	header : '报表',
        	menus : [{
        		id : '300',
        		text : '系统管理',
        		sref : 'app.report',
        		href : '/report',
        		children : [{
        			id : '300001',
        			text : '用户报表',
        			href : '/userReport',
        			sref : 'app.report.userReport',
        			tempUrl : 'tpl/user/userReport.html',
        			ctrl : 'js/controllers/user/userReportCtl.js'
        		}]
        	}]
        }]
      }
      
      global.menuData = $scope.app.menuData;

      // save settings to local storage
      if ( angular.isDefined($localStorage.settings) ) {
        $scope.app.settings = $localStorage.settings;
      } else {
        $localStorage.settings = $scope.app.settings;
      }
      $scope.$watch('app.settings', function(){
        if( $scope.app.settings.asideDock  &&  $scope.app.settings.asideFixed ){
          // aside dock and fixed must set the header fixed.
          $scope.app.settings.headerFixed = true;
        }
        // save to local storage
        $localStorage.settings = $scope.app.settings;
      }, true);

      // angular translate
      $scope.lang = { isopen: false };
      $scope.langs = {ch:'中文', en:'English'};
      $scope.selectLang = $scope.langs[$translate.proposedLanguage()] || "English";
      $scope.setLang = function(langKey, $event) {
        // set the current lang
        $scope.selectLang = $scope.langs[langKey];
        // You can change the language during runtime
        $translate.use(langKey);
        $scope.lang.isopen = !$scope.lang.isopen;
      };

      function isSmartDevice( $window )
      {
          // Adapted from http://www.detectmobilebrowsers.com
          var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
          // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
          return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
      }

  }]);