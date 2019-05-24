db.createCollection('user');
db.user.insert({
    login: 'denis',
    password: 'password',
    roles: ['ADMIN', 'PLAYER']
});
