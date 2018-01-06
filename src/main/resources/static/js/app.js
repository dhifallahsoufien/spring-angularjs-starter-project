/**
 * 
 */

var app = angular.module("MyApp", ["ngRoute","angularUtils.directives.dirPagination"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("#/!", {
        templateUrl : "index.html"
    })
//     $routeProvider
//    .when("#/dashbord", {
//        templateUrl : "dashbord.html"
//    })
    .when("/user", {
        templateUrl : "pages/user/user.html",
        controller : "UserController"
    })
     .when("/adduser", {
        templateUrl : "pages/user/adduser.html",
        controller : "UserController"
    })
         .when("/edituser", {
        templateUrl : "pages/user/edituser.html",
        controller : "UserController"
    })
        .when("/etudiant", {
        templateUrl : "pages/etudiant/etudiant.html",
        controller : "EtudiantController"
    })
            .when("/addetudiant", {
        templateUrl : "pages/etudiant/addetudiant.html",
        controller : "EtudiantController"
    })

}

);

/************************* EtudiantController ***********************/
app.controller("EtudiantController", function($scope, $http, $log) {
	$scope.errors = null;
	$scope.pageCourant = 0;
	$scope.etudiants = [];
	$scope.etudiant = {};

	$scope.saveEtudiant = function() {
		$http.post("/etudiants", $scope.etudiant).then(function(data) {
			if (!data.errors) {
				$scope.etudiant = null;
				$scope.errors = null;
				messagethen();
			} else {
				$scope.errors = data;
			}
		}).catch(function(data) {
			messageError(data.data.message);
		})
	};
	$scope.deleteEtudiant = function(id) {
		$http.delete("/etudiants/"+id).then(function(data) {
			messagethen();
			$scope.allEtudiants();
		}).catch(function(data) {
			messageError(data.data.message);
		})
	};
	$scope.allEtudiants = function() {
		$http.get("/etudiants?page=0&size=10000").then(function(data) {
			$scope.pageCourant = 0;
			$scope.etudiants = data;
			$scope.pages = new Array(data.totalPages);
		}).catch(function(data) {
			messageError(data.data.message);
		});
	};
	$scope.goToPageEtudiants = function(p) {
		$http.get("/etudiants?page=" + p + "&size=3").then(function(data) {
			$scope.pageCourant = p;
			$scope.etudiants = data;
		}).catch(function(data) {
			messageError(data.data.message);
		});
	};

});
/*****************************TemplateController**********************************/

app.controller("TemplateController", function($scope, $http, $log) {
	$scope.selectedMenu=null;
	$scope.select=function(m) {
		$scope.selectedMenu=m;
	};
});

/************************* EditUserController ***********************/
app.controller("EditUserController", function($scope, $http, $log,$window,$rootScope) {
	$scope.user = {};
    $scope.user = $rootScope.user ;
    $scope.user.confmPassword =  $rootScope.user.password ;

	$scope.editUser = function() {
		$http.put("/users", $scope.user).then(function(data) {
			if (!data.errors) {
				$scope.errors = null;
				messagethen();
				$scope.findAllRoles();
			} else {
				$scope.errors = data;
			}
		}).catch(function(data) {
			messageError(data.data.message);
		})
	};
	$scope.roles = [];
	$scope.findAllRoles = function() {
		$http.get("/roles").then(function(data) {
			$scope.roles = data;
		}).catch(function(data) {
			messageError(data.data.message);
		});
	};
	$scope.toggle = function(role) {

		var idx = $scope.user.role.indexOf(role);
		if (idx > -1) {
			$scope.user.role.splice(idx, 1);
		} else {
			$scope.user.role.push(role);
		}
		console.log($scope.user.role) ;
	};
	$scope.exists = function(role, list) {
		for(var r in list){
		var obj = list[r];
			if(obj.id==role.id){
				return true;
			}
		}
		return false;
	};
    
});

/************************** UserController ************************/
app.controller("UserController", function($scope, $http, $log,$window,$rootScope,$location) {
	$scope.errors = null;
	$scope.pageCourant = 0;
	$scope.user = {};
	$rootScope.userEdit={};
	$scope.userId =null;
	$scope.users = [];
	
	$scope.initEditUser = function(u) {
		$rootScope.user = u ;
	};

	$scope.initAddUser = function() {
		$scope.findAllRoles();
	};
	$scope.saveUser = function() {
		$scope.user.role= $scope.list;
		$http.post("/users", $scope.user).then(function(data) {
			if (!data.errors) {
				$scope.user = null;
				$scope.errors = null;
				messagethen();
				$scope.findAllRoles();
			} else {
				$scope.errors = data;
			}
		}).catch(function(data) {
			messageError(data.data.message);
		})
	};
	$scope.deleteUser = function(id) {
		$http.delete("/users/"+id).then(function(data) {
			messagethen();
			$scope.allUsers();
		}).catch(function(data) {
			messageError(data.data.message);
		})
	};
	
	$scope.editUser = function(user) {
		$http.put("/users", $scope.user).then(function(data) {
			messagethen();
			$scope.allUsers();
		}).catch(function(data) {
			messageError(data.data.message);
		})
	};
	
	$scope.getUser = function(id) {
		$http.get("/users/"+id).then(function(data) {
				$scope.user = data;
		}).catch(function(data) {
			messageError(data.data.message);
		})
	};
	$scope.list =  [];
	$scope.toggle = function(role) {

		var idx = $scope.list.indexOf(role);
		if (idx > -1) {
			$scope.list.splice(idx, 1);
		} else {
			$scope.list.push(role);
		}
		console.log($scope.list) ;
	};
	
	$scope.allUsers = function() {
		$http.get("/allusers?page=0&size=100000").then(function(data) {
			$scope.pageCourant = 0;
			$scope.users = data;
			$scope.pages = new Array(data.totalPages);
		}).catch(function(data) {
			messageError(data.data.message);
		});
	};
	$scope.goToPageUsers = function(p) {
		$http.get("/users?page=" + p + "&size=100").then(function(data) {
			$scope.pageCourant = p;
			$scope.users = data;
		}).catch(function(data) {
			messageError(data.data.message);
		});
	};

	/****** Role ******/
	$scope.roles = [];
	$scope.findAllRoles = function() {
		$http.get("/roles").then(function(data) {
			$scope.roles = data;
		}).catch(function(data) {
			messageError(data.data.message);
		});
	};
});

app.controller("ResetPasswordController", function($scope, $http, $log) {
	$scope.loginOrPassword = null;
	$scope.resetPassword = function(loginOrPassword) {
		$http.get("/users?loginOrPassword=" + loginOrPassword).then(function(data) {
			$scope.roles = data;
		}).catch(function(data) {
			messageError(data.data.message);
		});
	};
});
/********************************* Util méthode *********************************/
messagethen = function() {

	demo.initChartist();

	$.notify({
		icon : 'ti-thumb-up ',
		message : "Op\u00E9ration effectu\u00E9e avec succ\u00E8s."

	}, {
		type : 'success',
		timer : 4000
	});
};
messageError = function(msg) {

	demo.initChartist();

	$.notify({
		icon : 'ti-thumb-down',
		message : msg

	}, {
		type : 'danger',
		timer : 4000
	});
};
/** *********************** Service User ********************** */
app.service('userService', function() {
	  var userService ={};

	  var addUser = function(user) {
		  userService= user;
	  };

	  var getUser = function(){
	      return userService;
	  };

	  return {
		  addUser: addUser,
		  getUser: getUser
	  };

	});