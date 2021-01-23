const api = require('../controllers/card-controller')

module.exports = (app) => {
    app.route('/cards')
        .get(api.findAll)
        .post(api.save)

    app.route('/cards/:_id')
        .get(api.findOne)

    app.route('/cards/:_id')
        .delete(api.remove)

    app.route('/cards/:_id')
        .put(api.update)
}