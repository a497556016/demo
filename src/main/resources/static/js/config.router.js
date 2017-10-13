'use strict';

/**
 * Config for the router
 */
angular.module('app')
  .run(
    [          '$rootScope', '$state', '$stateParams',
      function ($rootScope,   $state,   $stateParams) {
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;      
          console.log($rootScope)
      }
    ]
  )
  .config(
    [          '$stateProvider', '$urlRouterProvider', 
      function ($stateProvider,   $urlRouterProvider) {
    	$urlRouterProvider
        .otherwise('/app/dashboard-v1');
    	
    	$stateProvider
        .state('app', {
            abstract: true,
            url: '/app',
            templateUrl: 'tpl/app.html'
        })
        .state('app.dashboard-v1', {
            url: '/dashboard-v1',
            templateUrl: 'tpl/app_dashboard_v1.html',
            resolve: {
              deps: ['$ocLazyLoad',
                function( $ocLazyLoad ){
                  return $ocLazyLoad.load(['js/controllers/chart.js']);
              }]
            }
        })
    	
    	setTimeout(function(){
    		console.log(global.menuData);
    		 
    		angular.forEach(global.menuData,function(menu){
    			angular.forEach(menu.menus,function(ms){
    				$stateProvider.state(ms.sref, {
    	                  url: ms.href,
    	                  template: '<div ui-view class="fade-in"></div>'/*,
    	                  resolve: {
    	                      deps: ['uiLoad',
    	                        function( uiLoad){
    	                          return uiLoad.load('js/controllers/form.js');
    	                      }]
    	                  }*/
    	            })
    				angular.forEach(ms.children,function(mc){
    					$stateProvider.state(mc.sref, {
    		                  url: mc.href,
    		                  templateUrl: mc.tempUrl,
    		                  resolve: {
    		                    deps: ['$ocLazyLoad',
    		                      function( $ocLazyLoad ){
    		                        return $ocLazyLoad.load([mc.ctrl]);
    		                    }]
    		                  }
    		            })
        			});
    			});
    		});
    	},200);
    
              
      }
    ]
  );