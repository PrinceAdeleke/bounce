const fs = require('fs');

const load = (file) => {
  return new Promise((resolve, reject) => {
    try {
      fs.readFile(file, 'utf8', function(err, contents) {
        if (err) return reject(err);
        resolve(contents.trim());
      });
    } catch(e) {
      reject(e);
    }
  });
};

module.exports = { load };
