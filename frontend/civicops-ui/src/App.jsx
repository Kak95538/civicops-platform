import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login.jsx';
import './App.css';

const services = [
  {
    icon: '🛣️',
    title: 'Road Damage',
    description: 'Report potholes, damaged roads, and unsafe streets.',
    color: 'orange',
  },
  {
    icon: '💡',
    title: 'Streetlights',
    description: 'Report broken streetlights and poorly lit public areas.',
    color: 'purple',
  },
  {
    icon: '💧',
    title: 'Water Issues',
    description: 'Report water leaks, drainage issues, and pipe damage.',
    color: 'blue',
  },
];

const workflow = [
  {
    number: '01',
    title: 'Report an issue',
    description: 'Citizens submit infrastructure complaints with location details.',
  },
  {
    number: '02',
    title: 'Assign maintenance',
    description: 'Department officers review complaints and assign work orders.',
  },
  {
    number: '03',
    title: 'Verify the repair',
    description: 'Field workers complete repairs and officers verify the results.',
  },
];

function Home() {
  return (
    <div className="app">
      <header className="navbar">
        <a className="brand" href="#home">
          <span className="brand-icon">C</span>
          <span>Civic<span className="brand-accent">Ops</span></span>
        </a>

        <nav className="nav-links">
          <a href="#home">Home</a>
          <a href="#services">Services</a>
          <a href="#how-it-works">How it works</a>
        </nav>

       <div className="nav-actions">
        <a className="nav-button" href="/login">
          Login
        </a>

        <a className="nav-button" href="#services">
            Explore services <span>↗</span>
        </a>
        </div>
      </header>

      <main>
        <section className="hero" id="home">
          <div className="hero-content">
            <div className="eyebrow">
              <span className="status-dot" />
              SMARTER CIVIC INFRASTRUCTURE
            </div>

            <h1>
              Better cities start
              <br />
              with <span>better action.</span>
            </h1>

            <p className="hero-description">
              One platform to report civic issues, coordinate maintenance,
              and build safer, cleaner communities.
            </p>

            <div className="hero-actions">
              <a className="primary-button" href="#services">
                Explore services <span>→</span>
              </a>
              <a className="secondary-button" href="#how-it-works">
                Discover how it works
              </a>
            </div>

            <div className="trust-note">
              <span className="trust-icon">✓</span>
              Transparent reporting. Accountable maintenance.
            </div>
          </div>

          <div className="hero-visual">
            <div className="visual-topline">
              <span className="live-dot" />
              Civic infrastructure
              <span className="live-label">OVERVIEW</span>
            </div>

            <div className="visual-main">
              <div className="city-icon">🏙️</div>
              <p className="visual-caption">A better-connected community</p>
              <h2>Every issue deserves action.</h2>
              <p className="visual-subtitle">
                From the first report to the final repair.
              </p>

              <div className="mini-stats">
                <div>
                  <span className="mini-icon">📍</span>
                  <strong>Report</strong>
                  <small>Identify issues</small>
                </div>
                <div>
                  <span className="mini-icon">🛠️</span>
                  <strong>Resolve</strong>
                  <small>Coordinate repairs</small>
                </div>
                <div>
                  <span className="mini-icon">✓</span>
                  <strong>Verify</strong>
                  <small>Confirm completion</small>
                </div>
              </div>
            </div>

            <div className="visual-footer">
              <span>Built for citizens and civic teams</span>
              <span>● Connected workflows</span>
            </div>
          </div>
        </section>

        <section className="services section" id="services">
          <div className="section-heading">
            <div>
              <span className="section-label">CIVIC SERVICES</span>
              <h2>Make your city better.</h2>
              <p>Start with the issues that matter in your neighbourhood.</p>
            </div>
            <span className="service-count">03 SERVICE CATEGORIES</span>
          </div>

          <div className="service-grid">
            {services.map((service) => (
              <article className="service-card" key={service.title}>
                <div className={`service-icon ${service.color}`}>
                  {service.icon}
                </div>
                <h3>{service.title}</h3>
                <p>{service.description}</p>
                <a href="#how-it-works" className="card-link">
                  Learn more <span>→</span>
                </a>
              </article>
            ))}
          </div>
        </section>

        <section className="workflow section" id="how-it-works">
          <div className="section-heading">
            <div>
              <span className="section-label">HOW CIVICOPS WORKS</span>
              <h2>From report to resolution.</h2>
              <p>A clear process that keeps every team moving forward.</p>
            </div>
          </div>

          <div className="workflow-grid">
            {workflow.map((step) => (
              <article className="workflow-card" key={step.number}>
                <span className="step-number">{step.number}</span>
                <h3>{step.title}</h3>
                <p>{step.description}</p>
              </article>
            ))}
          </div>
        </section>

        <section className="cta">
          <div>
            <span className="section-label">OUR MISSION</span>
            <h2>Small fixes. Stronger communities.</h2>
            <p>
              Bringing citizens, departments, and field teams together
              through one connected platform.
            </p>
          </div>
          <a className="cta-button" href="#home">
            Back to top ↑
          </a>
        </section>
      </main>

      <footer className="footer">
        <a className="brand footer-brand" href="#home">
          <span className="brand-icon">C</span>
          <span>Civic<span className="brand-accent">Ops</span></span>
        </a>
        <p>Designed for better civic infrastructure.</p>
        <span>© 2026 CivicOps · Project prototype</span>
      </footer>
    </div>
  );
}


function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
    </Routes>
  );
}

export default App;

