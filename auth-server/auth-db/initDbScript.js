// ToDo: Think how protect this data
db.createCollection('user');
db.user.insert({
    login: 'denisermolovich@gmail.com',
    password: '3BKeLeQ5ndT0oAeiZVpViEw0VIwOhF3gaPtZaeL4YHY=',
    salt: '3447d5f2-8396-4d60-812c-0d51d328ad4d',
    roles: ['ADMIN', 'PLAYER']
});
