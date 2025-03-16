const { spawn } = require("child_process");

module.exports = (req, res) => {
  const process = spawn("./target/your-springboot-app", []);

  process.stdout.on("data", (data) => {
    res.send(data.toString());
  });

  process.stderr.on("data", (data) => {
    console.error(`stderr: ${data}`);
  });
};