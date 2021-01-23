const neDB = require('../configurations/database')
const api = {}

api.findOne = (request, response) => {

    let x = request.params._id
    neDB.findOne({ _id: x }, (exception, customer ) => {

        if(exception) {
            const sentence = "Error";
            console.log(sentence, exception)
            response.status(exception.status | 400)
            response.json({'mensagem': sentence})
        }
        response.json(customer)
    } )
}

api.update = (request, response) => {

    let x = request.params._id
    let card2 = request.body.cardNumber
    let customer2 = request.body.customerName
    let document2 = request.body.documentNumber
    let mother2 = request.body.motherName
    let city2 = request.body.city
    let address2 = request.body.address
    neDB.update({ _id: x  }, { $set: { cardNumber: card2, customerName: customer2, documentNumber: document2, motherName: mother2, city: city2, address: address2 } }, { multi: true }, (exception, customer ) => {

        if(exception) {
            const sentence = "Error";
            console.log(sentence, exception)
            response.status(exception.status | 400)
            response.json({'mensagem': sentence})
        }
        response.json("Client updated")
    } )
}

api.remove = (request, response) => {

    let x = request.params._id
    neDB.remove({ _id: x }, (exception, customer ) => {

        if(exception) {
            const sentence = "Error";
            console.log(sentence, exception)
            response.status(exception.status | 400)
            response.json({'mensagem': sentence})
        }
        response.json("User has been deleted")
    } )
}

api.findAll = (request, response) => {
    neDB.find({}).sort({name: 1}).exec((exception, cards) => {
        if(exception) {
            const sentence = 'Error on show all clients!'
            console.log(sentence, exception)

            response.status(exception.status | 400)
            response.json({'mensagem': sentence})
        }
        response.json(cards)
    })

}

api.save = (request, response) => {
    const canonical = request.body

    neDB.insert(canonical, (exception, card) => {
        if(exception) {
            const sentence2 = 'Error on show a card!'
            console.log(sentence2, exception)

            response.status(exception.status | 400)
            response.json({'mensagem': sentence2})
        }

        response.json(card)
        response.status(201)
    })


}

module.exports = api