
import { useState } from 'react';
import { Link } from 'react-router-dom';
import './Login.css';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    alert('Login UI is ready. Backend integration comes next.');
  };

  return (
    <main className="login-page">
      <section className="login-card">
        <Link to="/" className="login-brand">
          <span className="login-brand-icon">C</span>
          <span>Civic<span>Ops</span></span>
        </Link>

        <div className="login-heading">
          <span className="login-eyebrow">CITIZEN & CIVIC TEAM PORTAL</span>
          <h1>Welcome back</h1>
          <p>Sign in to continue to CivicOps.</p>
        </div>

        <form className="login-form" onSubmit={handleSubmit}>
          <div className="login-field">
            <label htmlFor="email">Email address</label>
            <input
              id="email"
              type="email"
              autoComplete="username"
              placeholder="you@example.com"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              required
            />
          </div>

          <div className="login-field">
            <label htmlFor="password">Password</label>
            <input
              id="password"
              type="password"
              autoComplete="current-password"
              placeholder="Enter your password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              required
            />
          </div>

          <button className="login-submit" type="submit">
            Sign in <span>→</span>
          </button>
        </form>

        <div className="login-divider">
          <span />
          SECURE CIVIC ACCESS
          <span />
        </div>

        <p className="login-back">
          New to CivicOps?{' '}
          <Link to="/">Explore the platform</Link>
        </p>
      </section>

      <footer className="login-footer">
        © 2026 CivicOps · Building better communities
      </footer>
    </main>
  );
}

export default Login;
