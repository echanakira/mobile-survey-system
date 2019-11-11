const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

const db = admin.firestore();
const settings = { timestampInSnapshots: true };
db.settings(settings);

const storage = admin.storage();
const bucket = storage.bucket();

// delete user document from firestore when account deleted from firebase auth
exports.deleteUserDocument = functions.auth.user().onDelete(event => {
    console.log('User id to be deleted: ', event.uid);

    const userId = event.uid;

    return db.collection('users').doc(userId).delete().then(() => {
        console.log('Deleted user: ', userId);
        return `Deleted user $userID`;
    }).catch(error => {
        console.error('Error when delting user! $userID', error);
    });
});

// delete user account from firebase auth when user deleted from firestore
exports.deleteUserAccount = functions.firestore
    .document('users/{userID}')
    .onDelete((snap, context) => {
        const userId = snap.id;

        return admin.auth().deleteUser(snap.id)
            .then(() => console.log('Deleted user with ID:' + snap.id))
            .catch((error) => console.error('There was an error while deleting user:', error));
    });